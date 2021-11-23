package com.gomdolstudio.sellers.di.module

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gomdolstudio.covidinfoapp.di.factory.InjectingSavedStateViewModelFactory
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityContext
import com.gomdolstudio.musicapp_assistedinjection.di.scope.ActivityScope
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.databinding.ActivityProductdetailBinding
import com.gomdolstudio.sellers.databinding.ActivityProductmainBinding
import com.gomdolstudio.sellers.databinding.FragmentProductlistBinding
import com.gomdolstudio.sellers.di.scope.FragmentScope
import com.gomdolstudio.sellers.ui.product.ProductMainActivity
import com.gomdolstudio.sellers.ui.product.detail.ProductDetailActivity
import com.gomdolstudio.sellers.ui.product.list.ProductListFragment
import com.gomdolstudio.sellers.ui.signin.SigninActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
class ProductDetailModule {
    @Module
    companion object{
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideProductDetailActivityBinding(activity: ProductDetailActivity): ActivityProductdetailBinding {
            return DataBindingUtil.setContentView(activity, R.layout.activity_productdetail)
        }

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideViewModelProvider(activity: ProductDetailActivity, viewModelFactory: InjectingSavedStateViewModelFactory): ViewModelProvider {
            return ViewModelProvider(activity, viewModelFactory.create(activity))
        }


        @ActivityContext
        @JvmStatic
        @Provides
        fun provideContext(activity: ProductDetailActivity): Context {
            return activity
        }
    }


}