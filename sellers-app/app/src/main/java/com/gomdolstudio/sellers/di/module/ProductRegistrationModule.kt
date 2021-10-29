package com.gomdolstudio.sellers.di.module

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gomdolstudio.covidinfoapp.di.factory.InjectingSavedStateViewModelFactory
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityContext
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityScope
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.databinding.ActivityProductregistrationBinding
import com.gomdolstudio.sellers.ui.product.registration.ProductRegistrationActivity
import dagger.Module
import dagger.Provides

@Module
class ProductRegistrationModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideProductRegistrationActivityBinding(activity: ProductRegistrationActivity): ActivityProductregistrationBinding {
            return DataBindingUtil.setContentView(activity, R.layout.activity_productregistration)
        }

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideViewModelProvider(activity: ProductRegistrationActivity, viewModelFactory: InjectingSavedStateViewModelFactory): ViewModelProvider {
            return ViewModelProvider(activity, viewModelFactory.create(activity))
        }

        @ActivityContext
        @JvmStatic
        @Provides
        fun provideContext(activity: ProductRegistrationActivity): Context {
            return activity
        }
    }
}