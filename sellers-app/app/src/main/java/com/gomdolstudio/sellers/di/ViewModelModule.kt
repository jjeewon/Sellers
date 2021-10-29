package com.gomdolstudio.covidinfoapp.di

import androidx.lifecycle.ViewModel
import com.gomdolstudio.covidinfoapp.di.factory.AssistedSavedStateViewModelFactory
import com.gomdolstudio.sellers.ui.product.ProductMainViewModel
import com.gomdolstudio.sellers.ui.product.registration.ProductRegistrationViewModel
import com.gomdolstudio.sellers.ui.signin.SigninViewModel
import com.gomdolstudio.sellers.ui.signup.SignupViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds

@AssistedModule
@Module(includes = [AssistedInject_ViewModelModule::class])
abstract class ViewModelModule {

    // 일반 뷰모델들의 멀티 바인딩
    @Multibinds
    abstract fun bindsViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

    // AssistedInject로 관리하는 ViewModel Factory 멀티 바인딩
    @Multibinds
    abstract fun bindAssistedViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedSavedStateViewModelFactory<out ViewModel>>


    @Binds
    @IntoMap
    @ViewModelKey(SignupViewModel::class)
    abstract fun bindsSignupViewModel(factory: SignupViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(SigninViewModel::class)
    abstract fun bindsSigninViewModel(factory: SigninViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(ProductMainViewModel::class)
    abstract fun bindsProductMainViewModel(factory: ProductMainViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(ProductRegistrationViewModel::class)
    abstract fun bindsProductRegistrationViewModel(factory: ProductRegistrationViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

}