package com.gomdolstudio.sellers.ui.product.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_productlist.view.*
import kotlinx.android.synthetic.main.item_productdetail.view.*
import kotlinx.android.synthetic.main.item_productlist.view.*
import android.graphics.drawable.Drawable
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.request.target.Target
import com.gomdolstudio.sellers.R


import android.widget.TextView
import com.gomdolstudio.sellers.api.response.ProductListItemResponse
import com.gomdolstudio.sellers.data.product.ProductStatus
import com.gomdolstudio.sellers.ui.product.list.pager.ProductListPagedAdapter
import java.text.NumberFormat


class ImageSliderAdapter : RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {
    private val imageUrls: MutableList<String> = ArrayList<String>()

    fun setItems(items: ArrayList<String>?){
        items?.let{
            imageUrls.clear()
            imageUrls.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.gomdolstudio.sellers.R.layout.item_productdetail, parent, false)
        return ImageSliderAdapter.ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageUrls[position])
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    class ImageViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(
        view
    ){
        fun bind(item: String?) = item?.let {
            val imageView = itemView.product_image_iv
            imageView?.let{
                Glide.with(it)
                    .load("http://10.0.2.2:8080${item}")
                    .into(it)
            }
        }

    }


}