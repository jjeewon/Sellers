package com.gomdolstudio.sellers.ui.product.registration

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gomdolstudio.covidinfoapp.di.factory.AssistedSavedStateViewModelFactory
import com.gomdolstudio.sellers.util.SingleLiveEvent
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class ProductRegistrationViewModel @AssistedInject constructor(@Assisted private val savedStateHandle: SavedStateHandle
): ViewModel(){

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<ProductRegistrationViewModel>
}