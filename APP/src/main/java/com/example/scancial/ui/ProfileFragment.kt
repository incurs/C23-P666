package com.example.scancial.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.scancial.R
import com.example.scancial.core.entity.Product
import com.example.scancial.databinding.FragmentProfileBinding
import com.example.scancial.utils.DummyData
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val fragment = this

//    private val authViewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initProfile()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initProfile() = with(binding) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        tvUserName.text = currentUser?.displayName
    }
    private fun initView() =

        with(binding) {
            imgUser.loadImage(DummyData.Image()[0])
        tvEditProfile.setOnClickListener(fragment)
        tvChangePassword.setOnClickListener(fragment)
        tvBankInformation.setOnClickListener(fragment)
        tvUserName.setOnClickListener(fragment)
        btnLogout.setOnClickListener(fragment)
    }
    private fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .centerCrop()
            .into(this)
    }

    override fun onClick(v: View?) {
        with(binding) {
            when (v) {
                tvEditProfile -> {}
                tvChangePassword -> {}
                tvBankInformation -> {}
                tvUserName -> {}
                btnLogout -> logout()
                else -> return
            }
        }
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val loginIntent = Intent(requireActivity(), LoginActivity::class.java)
        startActivity(loginIntent)
        requireActivity().finishAffinity()
    }
}