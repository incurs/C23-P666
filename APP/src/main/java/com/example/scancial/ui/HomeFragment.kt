package com.example.scancial.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scancial.core.entity.Product
import com.example.scancial.core.entity.Tips
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
    private fun initProfile() {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val photoUrl = currentUser.photoUrl?.toString()
            if (!photoUrl.isNullOrEmpty()) {
                binding.igUser.loadImage(photoUrl)
            } else {
                binding.igUser.loadImage(DummyData.Image()[0])
            }

            val userName = currentUser.displayName
            val userEmail = currentUser.email

            if (!userName.isNullOrEmpty()) {
                binding.tvName.text = userName
            } else if (userEmail.isNullOrEmpty()) {
                binding.tvName.text = userEmail
            }
        }
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
    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .centerCrop()
            .into(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        binding.rvTrick.setOnClickListener {
            val trick = Intent(requireActivity(), TipsTrikFragment::class.java)
            startActivity(trick)
        }
    }

    override fun onClickProduct(product: Product) {
        val detailIntent = Intent(requireActivity(), DetailProdukActivity::class.java)
        startActivity(detailIntent)
    }
}