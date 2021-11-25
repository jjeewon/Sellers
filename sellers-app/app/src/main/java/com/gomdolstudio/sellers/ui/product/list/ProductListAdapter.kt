package com.gomdolstudio.sellers.ui.product.list
import android.os.Bundle
import com.gomdolstudio.sellers.ui.product.list.pager.ProductListPagerFragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gomdolstudio.sellers.data.category.categoryList

class ProductListAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
       return categoryList.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ProductListPagerFragment()
        fragment.arguments = Bundle().apply {
            putInt("pos", position)
        }
        return fragment
    }
}