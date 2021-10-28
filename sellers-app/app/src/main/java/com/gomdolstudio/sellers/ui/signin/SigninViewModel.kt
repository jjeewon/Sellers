package com.gomdolstudio.sellers.ui.signin


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gomdolstudio.covidinfoapp.di.factory.AssistedSavedStateViewModelFactory
import com.gomdolstudio.sellers.api.request.SigninRequest
import com.gomdolstudio.sellers.api.response.ApiResponse
import com.gomdolstudio.sellers.api.response.SigninResponse
import com.gomdolstudio.sellers.data.service.SigninService
import com.gomdolstudio.sellers.util.SingleLiveEvent
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SigninViewModel @AssistedInject constructor(@Assisted private val savedStateHandle: SavedStateHandle,
                                                  private val signinService: SigninService): ViewModel(){
    private val compositeDisposable = CompositeDisposable()

    val inputEmail: MutableLiveData<String> = MutableLiveData("")
    val inputPassword: MutableLiveData<String> = MutableLiveData("")

    private val showToast = SingleLiveEvent<String>()
    fun getShowToast(): SingleLiveEvent<String>{
        return showToast
    }
    fun signin(){
        val request = SigninRequest(inputEmail.value!!,inputPassword.value!!)
        if (isNotValidSignin(request)) return
        requestSignin(request)
    }

    fun isNotValidSignin(signinRequest: SigninRequest) =
            when {
                signinRequest.isNotValidEmail() -> {
                    showToast.postValue( "이메일 형식이 정확하지 않습니다.")
                    true
                }
                signinRequest.isNotValidPassword() -> {
                    showToast.postValue( "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
                    true
                }
                else -> false
            }

    fun requestSignin(request: SigninRequest){
        compositeDisposable.add(signinService.signin(request)
                .subscribeOn(Schedulers.io())
                .subscribe(::onSigninResponse))
    }
    private fun onSigninResponse(response: ApiResponse<SigninResponse>) {
        if (response.success) {
            showToast.postValue( "로그인되었습니다.")
            // TODO. 상품 리스트 화면으로 이동
        } else {
            showToast.postValue( response.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<SigninViewModel>
}