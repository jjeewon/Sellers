package com.gomdolstudio.sellers.ui.product.list.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.gomdolstudio.sellers.R
import com.gomdolstudio.sellers.databinding.FragmentProductlistBinding
import com.gomdolstudio.sellers.databinding.FragmentProductlistpagerBinding


class ProdctListPagerFragment : Fragment() {
    lateinit var binding: FragmentProductlistpagerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_productlistpager,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey("pos") }?.apply {
            binding.haha.text = getInt("pos").toString()
            //val textView: TextView = view.findViewById(android.R.id.haha)
        }
    }
}