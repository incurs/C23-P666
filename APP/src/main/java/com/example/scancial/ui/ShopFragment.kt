package com.example.scancial.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scancial.R
import com.example.scancial.core.entity.Product
import com.example.scancial.databinding.FragmentHomeBinding
import com.example.scancial.databinding.FragmentShopBinding
import com.example.scancial.ui.adapter.ProductAdapter
import com.example.scancial.utils.DummyData
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

class ShopFragment : Fragment(),View.OnClickListener, ProductAdapter.ProductClickCallback {
    private var _binding : FragmentShopBinding? = null
    private val binding get() = _binding!!
    private val productAdapter = ProductAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        DummyData.apply {
            productAdapter.setData(Product())
        }
        with(binding.rvProduct){
            adapter = productAdapter
            layoutManager = FlexboxLayoutManager(requireContext(),
                com.google.android.flexbox.FlexDirection.ROW, com.google.android.flexbox.FlexWrap.WRAP)
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onClickProduct(product: Product) {
        TODO("Not yet implemented")
    }
}