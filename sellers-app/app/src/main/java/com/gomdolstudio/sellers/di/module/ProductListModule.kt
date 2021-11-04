package com.gomdolstudio.sellers.di.module

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gomdolstudio.covidinfoapp.di.factory.InjectingSavedStateViewModelFactory
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.databinding.FragmentProductlistBinding
import com.gomdolstudio.sellers.di.scope.FragmentScope
import com.gomdolstudio.sellers.ui.product.ProductMainActivity
import dagger.Module
import dagger.Provides

@Module
class ProductListModule {
    @Provides
    @FragmentScope
    fun provideProductListFragmentBinding(activity: ProductMainActivity): FragmentProductlistBinding {
        return DataBindingUtil.inflate<FragmentProductlistBinding>(
            activity.layoutInflater,
            R.layout.fragment_productlist,
            null,
            false
        )
    }


    @Provides
    @FragmentScope
    fun provideViewModelProvider(activity:ProductMainActivity, viewModelFactory: InjectingSavedStateViewModelFactory):ViewModelProvider{
        return ViewModelProvider(activity, viewModelFactory.create(activity))
    }


    @Provides
    @FragmentScope
    fun provideLinearLayoutManager(activity: ProductMainActivity): LinearLayoutManager {
        return LinearLayoutManager(activity)
    }



}