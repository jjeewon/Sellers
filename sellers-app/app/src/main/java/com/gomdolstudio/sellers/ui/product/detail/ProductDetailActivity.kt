package com.gomdolstudio.sellers.ui.product.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.databinding.ActivityProductdetailBinding
import com.gomdolstudio.sellers.ui.product.list.ProductListFragment
import com.google.android.material.navigation.NavigationView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ProductDetailActivity  : DaggerAppCompatActivity(){
    @Inject
    lateinit var binding: ActivityProductdetailBinding
    @Inject
    lateinit var viewModelProvider: ViewModelProvider
    private lateinit var productDetailViewModel: ProductDetailViewModel
    private lateinit var adapter: ImageSliderAdapter
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productDetailViewModel = viewModelProvider.get(ProductDetailViewModel::class.java)
        binding.lifecycleOwner = this
        binding.productDetailViewModel = productDetailViewModel
        adapter = ImageSliderAdapter()
        viewPager = binding.pager
        viewPager.adapter = adapter

        productDetailViewModel.imageUrlsLiveData.observe(this,
        Observer { list -> adapter.setItems(list as ArrayList<String>) })

        if (intent.hasExtra("item_id")) {
            val id = intent.getLongExtra("item_id", (-1).toLong())
            if (id != (-1).toLong()) productDetailViewModel.loadDetail(id)
        }


    }
}