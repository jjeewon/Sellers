package com.gomdolstudio.sellers.ui.product.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gomdolstudio.sellers.databinding.FragmentProductlistBinding
import com.gomdolstudio.sellers.ui.product.ProductMainActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProductListFragment: DaggerFragment() {
    @Inject
    lateinit var binding: FragmentProductlistBinding

    @Inject
    lateinit var viewModelProvider: ViewModelProvider
    private lateinit var productListViewModel: ProductListViewModel

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    override fun onAttach(context: Context) {
        super.onAttach(context)


    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // binding.아이디.로 조작...
        if (savedInstanceState == null) {
        }
        binding.lifecycleOwner = this
        binding.productListViewMdoel = productListViewModel
        productListViewModel.getRegistrationClickEvent().observe(this, Observer {
            (activity as ProductMainActivity).moveToRegistrationActivity()
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productListViewModel = viewModelProvider.get(ProductListViewModel::class.java)

    }


}