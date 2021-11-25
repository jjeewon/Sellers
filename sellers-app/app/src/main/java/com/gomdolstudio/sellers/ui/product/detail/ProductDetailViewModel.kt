package com.gomdolstudio.sellers.ui.product.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gomdolstudio.covidinfoapp.di.factory.AssistedSavedStateViewModelFactory
import com.gomdolstudio.sellers.api.response.ApiResponse
import com.gomdolstudio.sellers.api.response.ProductResponse
import com.gomdolstudio.sellers.common.Prefs
import com.gomdolstudio.sellers.data.product.ProductStatus
import com.gomdolstudio.sellers.data.service.ProductService
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.NumberFormat

class ProductDetailViewModel  @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val productService: ProductService
): ViewModel(){
    private val compositeDisposable = CompositeDisposable()

    var productId: Long? = null
    val productNameLiveData: MutableLiveData<String> = MutableLiveData("-")
    val descriptionLiveData: MutableLiveData<String> = MutableLiveData("")
    val priceLiveData: MutableLiveData<String> = MutableLiveData("-")
    val imageUrlsLiveData: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())
    val userNameLiveData: MutableLiveData<String> = MutableLiveData()
    fun loadDetail(id: Long){
        compositeDisposable.add(productService
            .getProduct(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::dd,::onError))
    }

    fun dd(ss: ApiResponse<ProductResponse>){
        val a = ss
        val b = ss
        if(ss.success){
            ss.data?.let { updateViewData(it) }
        }

    }

    private fun updateViewData(product: ProductResponse){
        userNameLiveData.value = Prefs.userName
        val commaSeparatePrice =
            NumberFormat.getInstance().format(product.price)
        val soldOutString =
            if(ProductStatus.SOLD_OUT == product.status) "(품절)" else ""
        productNameLiveData.value = product.name
        descriptionLiveData.value = product.description
        priceLiveData.value =
            "₩${commaSeparatePrice} $soldOutString"
        imageUrlsLiveData.value = product.imagePaths as MutableList<String>
    }

    fun onError(t: Throwable?) {
        Log.d("어이없네.. ",t?.stackTrace.toString())
    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<ProductDetailViewModel>
}