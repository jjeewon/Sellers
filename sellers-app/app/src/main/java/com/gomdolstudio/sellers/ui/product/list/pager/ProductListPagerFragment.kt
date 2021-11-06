package com.gomdolstudio.sellers.ui.product.list.pager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.api.response.ProductListItemResponse
import com.gomdolstudio.sellers.databinding.FragmentProductlistBinding
import com.gomdolstudio.sellers.databinding.FragmentProductlistpagerBinding


class ProductListPagerFragment : Fragment() {
    lateinit var binding: FragmentProductlistpagerBinding
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var productListPagerViewmodel: ProductListPagerViewmodel
    lateinit var adapter: ProductListPagedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productListPagerViewmodel = ProductListPagerViewmodel()
        adapter = ProductListPagedAdapter(productListPagerViewmodel)
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_productlistpager,container,false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey("pos") }?.apply {
            productListPagerViewmodel.categoryId = getInt("pos")
            //val textView: TextView = view.findViewById(android.R.id.haha)
        }
        binding.lifecycleOwner = this
        binding.viewModel = productListPagerViewmodel
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = linearLayoutManager

        productListPagerViewmodel.products.observe( viewLifecycleOwner, Observer {
            pagedList: PagedList<ProductListItemResponse> -> adapter.submitList(pagedList)
        })

    }


}