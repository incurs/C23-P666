package com.example.scancial.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scancial.R
import com.example.scancial.databinding.ActivitySplashScreenBinding
import com.example.scancial.databinding.FragmentTipsTrikBinding


class TipsTrikFragment : Fragment(),View.OnClickListener {

    lateinit var binding: FragmentTipsTrikBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentTipsTrikBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        binding.root

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}