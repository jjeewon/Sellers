package com.gomdolstudio.sellers.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gomdolstudio.covidinfoapp.di.factory.AssistedSavedStateViewModelFactory
import com.gomdolstudio.sellers.api.request.SignupRequest
import com.gomdolstudio.sellers.api.response.ApiResponse
import com.gomdolstudio.sellers.data.service.SignupService
import com.gomdolstudio.sellers.util.SingleLiveEvent
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SignupViewModel @AssistedInject constructor(@Assisted private val savedStateHandle: SavedStateHandle,
                                                private val signupService: SignupService): ViewModel(){
    private val compositeDisposable = CompositeDisposable()

    val inputEmail: MutableLiveData<String> = MutableLiveData("")
    val inputName: MutableLiveData<String> = MutableLiveData("")
    val inputPassword: MutableLiveData<String> = MutableLiveData("")

    private val showToast = SingleLiveEvent<String>()
    fun getShowToast(): SingleLiveEvent<String>{
        return showToast
    }

    fun signup(){
        val request = SignupRequest(inputEmail.value!!,inputPassword.value!!,inputName.value!!)
        if (isNotValidSignup(request)) return
        requestSignup(request)
    }
    private fun isNotValidSignup(signupRequest: SignupRequest) =
        when {
            signupRequest.isNotValidEmail() -> {
                showToast.postValue( "이메일 형식이 정확하지 않습니다.")
                true
            }
            signupRequest.isNotValidName() -> {
                showToast.postValue( "이름은 2자 이상 20자 이하로 입력해주세요.")
                true
            }
            signupRequest.isNotValidPassword() -> {
                showToast.postValue( "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
                true
            }
            else -> false
        }
    private fun requestSignup(request: SignupRequest){
        compositeDisposable.add(signupService.signup(request)
            .subscribeOn(Schedulers.io())
            .subscribe(::onSignupResponse))
    }
    private fun onSignupResponse(response: ApiResponse<Void>) {
        if (response.success) {
            showToast.postValue( "회원 가입이 되었습니다. 로그인 후 이용해주세요.")
        } else {
            showToast.postValue( response.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }


    fun loadName(): String{
        val email = inputEmail.value!!
        val name = inputName.value!!
        val password = inputPassword.value!!

        return inputName.value!!
    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<SignupViewModel>
}