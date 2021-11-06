package com.gomdolstudio.sellers.ui.product.list.pager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.api.response.ProductListItemResponse
import com.gomdolstudio.sellers.common.LiveDataPagedListBuilder
import com.gomdolstudio.sellers.data.product.ProductStatus
import kotlinx.android.synthetic.main.item_productlist.view.*
import java.text.NumberFormat
import com.bumptech.glide.Glide

class ProductListPagedAdapter(
    private val listener: OnItemClickListener
) : PagedListAdapter<ProductListItemResponse,
        ProductListPagedAdapter.ProductItemViewHolder>(
        DIFF_CALLBACK
        )
{
    class ProductItemViewHolder(
        view: View,
        private val listener: OnItemClickListener,
    ) : RecyclerView.ViewHolder(
        view
    ){
        var productId: Long? = null
        init {
            itemView.setOnClickListener { listener.onClickProduct(productId) }
        }
        fun bind(item: ProductListItemResponse?) = item?.let {
            this.productId = item.id
            val soldOutString =
                if(ProductStatus.SOLD_OUT == item.status) "(품절)" else ""
            val commaSeparatedPrice =
                NumberFormat.getNumberInstance().format(item.price)
            itemView.product_name_tv.text = item.name
            itemView.product_price_tv.text =  "₩$commaSeparatedPrice $soldOutString"

            Glide.with(itemView.product_iv)
                .load("http://10.0.2.2:8080${item.imagePaths.firstOrNull()}")
                .into(itemView.product_iv)
                //.centerCrop()
        }

    }



    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_productlist, parent, false)
        return ProductItemViewHolder(view, listener)
    }

    interface OnItemClickListener{
        fun onClickProduct(productId: Long?)
    }

    interface ProductLiveDataBuilder
        : LiveDataPagedListBuilder<Long, ProductListItemResponse>

    companion object {
        val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ProductListItemResponse>() {
                override fun areItemsTheSame(
                    oldItem: ProductListItemResponse,
                    newItem: ProductListItemResponse
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: ProductListItemResponse,
                    newItem: ProductListItemResponse
                ) = oldItem.toString() == newItem.toString()
            }
    }

}