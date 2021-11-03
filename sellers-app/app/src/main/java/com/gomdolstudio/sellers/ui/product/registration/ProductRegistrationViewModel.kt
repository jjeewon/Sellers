package com.gomdolstudio.sellers.ui.product.registration

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gomdolstudio.covidinfoapp.di.factory.AssistedSavedStateViewModelFactory
import com.gomdolstudio.sellers.api.request.ProductRegistrationRequest
import com.gomdolstudio.sellers.api.request.SigninRequest
import com.gomdolstudio.sellers.api.request.SignupRequest
import com.gomdolstudio.sellers.api.response.ApiResponse
import com.gomdolstudio.sellers.api.response.ProductImageUploadResponse
import com.gomdolstudio.sellers.api.response.SigninResponse
import com.gomdolstudio.sellers.common.Prefs
import com.gomdolstudio.sellers.data.category.categoryList
import com.gomdolstudio.sellers.data.service.ProductImageUploadService
import com.gomdolstudio.sellers.data.service.ProductRegisterService
import com.gomdolstudio.sellers.util.SingleLiveEvent
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import java.io.File
import java.util.concurrent.TimeUnit


class ProductRegistrationViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val productImageUploadService: ProductImageUploadService,
    private val productRegisterService: ProductRegisterService
): ViewModel(){
    val compositeDisposable = CompositeDisposable()
    private val categories = MutableLiveData(categoryList.map { it.name })
    private var categoryIdSelected: Int? = categoryList[0].id
    private val showToast = SingleLiveEvent<String>()
    val imageUrls: List<MutableLiveData<String?>> = listOf(
        MutableLiveData(null as String?),
        MutableLiveData(null as String?),
        MutableLiveData(null as String?),
        MutableLiveData(null as String?)
    )
    val imageIds: MutableList<Long?> =
        mutableListOf(null, null, null, null)
    var current_iv_id = -1
    val inputName: MutableLiveData<String> = MutableLiveData("")
    val inputDescription: MutableLiveData<String> = MutableLiveData("")
    val inputPrice: MutableLiveData<String> = MutableLiveData("")
    val descriptionLimit = 500
    val productNameLimit = 40
    val REQUEST_PICK_IMAGES = 0
    val productNameLength: MutableLiveData<String> = MutableLiveData("0")
    val productDescriptionLength: MutableLiveData<String> = MutableLiveData("0")
    private val imageViewClickEvent = SingleLiveEvent<Int>()
    fun getShowToast(): SingleLiveEvent<String>{
        return showToast
    }
    private val registrationClickEvent = SingleLiveEvent<Void>()
    fun getRegistrationClickEvent(): SingleLiveEvent<Void>{
        return registrationClickEvent
    }
    fun getImageFromGallery(num: Int){
        imageViewClickEvent.postValue(num)
    }
    fun getImageViewClickEvent(): SingleLiveEvent<Int>{
        return imageViewClickEvent
    }
    fun getCategories(): MutableLiveData<List<String>>{
        return categories
    }

    fun updateNameLength(name: String){
        productNameLength.value = name.length.toString() + "/" + productNameLimit
    }

    fun updateDescriptionLength(description: String){
        productDescriptionLength.value = description.length.toString() + "/" + descriptionLimit
    }

    fun onSelectCategoryItem(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        categoryIdSelected = categoryList[pos].id
    }

    fun imageUpload(imageFile: File){
        val multipartBody = makeImagePart(imageFile)
        multipartBody?.let { requestImageUpload(it) }
    }

    fun makeImagePart(imageFile: File): MultipartBody.Part {
        val mediaType = "multipart/form-data".toMediaTypeOrNull()
        val body = imageFile.asRequestBody(mediaType)
        return MultipartBody.Part
            .createFormData("image", imageFile.name, body)
    }

    private fun requestImageUpload(image: MultipartBody.Part){
        compositeDisposable.add(productImageUploadService.uploadProductImage(image)
            .doOnError(::onError)
            .subscribeOn(Schedulers.io())
            .subscribe(::onImageUploadResponse))
    }

    fun onError(e: Throwable){
        Log.d("image",e.message.toString())
    }
    private fun onImageUploadResponse(response: ApiResponse<ProductImageUploadResponse>) {
        if (response.success && response.data != null) {
            Log.d("imageregistration", "이미지 업로드 성공")
            imageUrls[current_iv_id].postValue(response.data.filePath)
            imageIds[current_iv_id] = response.data.productImageId
        } else {
            Log.d("imageregistration", response.message + "?: 알 수 없는 오류가 발생했습니다.")
        }
    }

    fun register(){
        val request = ProductRegistrationRequest(
            inputName.value,
            inputDescription.value,
            inputPrice.value?.toIntOrNull(),
            categoryIdSelected,
            imageIds
            )
        if (isNotValidRegistration(request)) return
        requestRegistration(request)
    }
    fun requestRegistration(request: ProductRegistrationRequest){
        compositeDisposable.add(productRegisterService.registerProduct(request)
            .subscribeOn(Schedulers.io())
            .subscribe(::onProductRegistrationResponse))
    }

    private fun onProductRegistrationResponse(response: ApiResponse<Response<Void>>) {
        if (response.success) {
            showToast.postValue( "상품이 등록되었습니다.")
            //moveToProductMainEvent.postValue(null)
        } else {
            showToast.postValue( response.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }


    private fun isNotValidRegistration(request: ProductRegistrationRequest) =
        when {
            request.isNotValidName -> {
                showToast.postValue( "상품명을 조건에 맞게 입력해주세요. ")
                true
            }
            request.isNotValidDescription -> {
                showToast.postValue( "상품 설명을 조건에 맞게 입력해주세요.")
                true
            }
            request.isNotValidPrice -> {
                showToast.postValue( "가격을 조건에 맞게 입력해주세요 .")
                true
            }
            request.isNotValidCategoryId -> {
                showToast.postValue( "카테고리 아이디를 선택해주세요 .")
                true
            }
            request.isNotValidImageIds -> {
                showToast.postValue( "이미지를 한 개 이상 등록해주세요  .")
                true
            }
            else -> false
        }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<ProductRegistrationViewModel>
}