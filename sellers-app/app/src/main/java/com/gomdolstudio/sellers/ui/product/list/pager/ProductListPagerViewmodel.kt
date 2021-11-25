package com.gomdolstudio.sellers.ui.product.list.pager

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import com.gomdolstudio.sellers.api.response.ProductListItemResponse
import com.gomdolstudio.sellers.data.service.ProductListItemDataSource

class ProductListPagerViewmodel :
    ViewModel(),
    ProductListPagedAdapter.ProductLiveDataBuilder,
    ProductListPagedAdapter.OnItemClickListener
{
    var categoryId: Int = -1
    var products = buildPagedList()
    private val productItemClickedEvent: MutableLiveData<Long> = MutableLiveData()
    fun getProductItemClickedEvent(): MutableLiveData<Long> = productItemClickedEvent
    override fun createDataSource(): DataSource<Long, ProductListItemResponse> {
        if (categoryId == -1)
            error(
                Log.d("categoryId error","categoryId is -1")

            )
        return ProductListItemDataSource(categoryId)
    }

    override fun onClickProduct(productId: Long?) {
        Log.d("onClicked","productId ${productId} clicked")
        productItemClickedEvent.postValue(productId)
    }

}