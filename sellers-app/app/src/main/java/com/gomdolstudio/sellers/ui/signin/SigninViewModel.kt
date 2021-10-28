package com.gomdolstudio.sellers.ui.signin


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gomdolstudio.covidinfoapp.di.factory.AssistedSavedStateViewModelFactory
import com.gomdolstudio.sellers.data.service.SigninService
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject


class SigninViewModel @AssistedInject constructor(@Assisted private val savedStateHandle: SavedStateHandle,
                                                  private val signinService: SigninService): ViewModel(){
    val inputEmail: MutableLiveData<String> = MutableLiveData("")
    val inputPassword: MutableLiveData<String> = MutableLiveData("")


    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<SigninViewModel>
}