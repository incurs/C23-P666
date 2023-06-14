package com.example.scancial.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.scancial.core.entity.Product
import com.example.scancial.databinding.FragmentHomeBinding
import com.example.scancial.ui.adapter.ProductAdapter
import com.example.scancial.ui.adapter.TipsAdapter
import com.example.scancial.utils.DummyData
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment(),View.OnClickListener, ProductAdapter.ProductClickCallback {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val productAdapter = ProductAdapter(this)
    private val tipsAdapter = TipsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initProfile()
    }

    private fun initProfile() = with(binding) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        tvName.text = currentUser?.displayName
    }

    private fun initView() {
        DummyData.apply {
            productAdapter.setData(Product())
            tipsAdapter.setData(Tips())
        }

        binding.rvTrick.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = tipsAdapter
        }

        binding.rvProduct.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = productAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    override fun onClickProduct(product: Product) {
        val detailIntent = Intent(requireActivity(), DetailProdukActivity::class.java)
        startActivity(detailIntent)
    }
}