package com.gomdolstudio.sellers.ui.product.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gomdolstudio.covidinfoapp.di.factory.AssistedSavedStateViewModelFactory
import com.gomdolstudio.sellers.data.service.ProductRegisterService
import com.gomdolstudio.sellers.util.SingleLiveEvent
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class ProductListViewModel @AssistedInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel(){
    private val registrationClickEvent = SingleLiveEvent<Void>()
    fun getRegistrationClickEvent(): SingleLiveEvent<Void> {
        return registrationClickEvent
    }
    fun register(){
        registrationClickEvent.call()
    }

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<ProductListViewModel>
}