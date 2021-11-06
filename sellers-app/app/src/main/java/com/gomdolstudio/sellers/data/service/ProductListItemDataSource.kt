package com.gomdolstudio.sellers.data.service

import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gomdolstudio.sellers.api.ApiTokenInterceptor
import com.gomdolstudio.sellers.api.response.ApiResponse
import com.gomdolstudio.sellers.api.response.ProductListItemResponse
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

class ProductListItemDataSource(
    private val categoryId: Int?
    ) : PageKeyedDataSource<Long, ProductListItemResponse>() {
    lateinit var productListService: ProductListService



    private fun getProducts(id: Long, direction: String) = runBlocking {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(
            ApiTokenInterceptor()
        ).connectTimeout(2000L, TimeUnit.SECONDS) .build()
        try{
            productListService = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ProductListService::class.java)
            productListService.getProducts(id, categoryId, direction)
        } catch (e: Exception){
            ApiResponse.error<List<ProductListItemResponse>>(
                "알 수 없는 오류가 발생했습니다."
            )
        }
    }

    private fun showErrorMessage(
        response: ApiResponse<List<ProductListItemResponse>>
    ){
        Log.d("product list api",response.message ?: "알 수 없는 오류가 발생했습니다. ")
    }

    companion object{
        private const val NEXT ="next"
        private const val PREV = "prev"
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, ProductListItemResponse>
    ) {
        val response = getProducts(params.key, NEXT)
        if (response.success) {
            response.data?.let{
                if (it.isNotEmpty())
                    callback.onResult(it, it.last().id)
            }
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                showErrorMessage(response)
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, ProductListItemResponse>
    ) {
        val response = getProducts(params.key, PREV)
        if (response.success) {
            response.data?.let{
                if (it.isNotEmpty())
                    callback.onResult(it, it.first().id)
            }
        } else {
            GlobalScope.launch(Dispatchers.Main){
                showErrorMessage(response)
            }
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, ProductListItemResponse>
    ) {
        val response = getProducts(Long.MAX_VALUE, NEXT)
        if (response.success) {
            response.data?.let {
                if (it.isNotEmpty())
                    callback.onResult(it, it.first().id, it.last().id)
            }
        } else {
            GlobalScope.launch(Dispatchers.Main){
                showErrorMessage(response)
            }
        }
    }


}