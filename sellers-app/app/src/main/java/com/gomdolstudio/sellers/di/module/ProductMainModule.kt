package com.gomdolstudio.sellers.di.module

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gomdolstudio.covidinfoapp.di.factory.InjectingSavedStateViewModelFactory
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityContext
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityScope
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.databinding.ActivityProductmainBinding
import com.gomdolstudio.sellers.databinding.ActivitySigninBinding
import com.gomdolstudio.sellers.ui.product.ProductMainActivity
import com.gomdolstudio.sellers.ui.signin.SigninActivity
import dagger.Module
import dagger.Provides

@Module
class ProductMainModule {
    @Module
    companion object{
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideProductMainActivityBinding(activity: ProductMainActivity): ActivityProductmainBinding {
            return DataBindingUtil.setContentView(activity, R.layout.activity_productmain)
        }
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideViewModelProvider(activity: ProductMainActivity, viewModelFactory: InjectingSavedStateViewModelFactory): ViewModelProvider {
            return ViewModelProvider(activity, viewModelFactory.create(activity))
        }

        @ActivityContext
        @JvmStatic
        @Provides
        fun provideContext(activity: ProductMainActivity): Context {
            return activity
        }
    }
}