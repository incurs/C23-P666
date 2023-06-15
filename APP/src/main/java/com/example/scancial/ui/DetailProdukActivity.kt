package com.example.scancial.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.scancial.R
import com.example.scancial.databinding.ActivityDetailProdukBinding

class DetailProdukActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailProdukBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailProdukBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setStatusBarColor(fragment = Fragment())

    }
    @Suppress("DEPRECATION")
    private fun setStatusBarColor(fragment: Fragment?) {
        window.statusBarColor = getColor(R.color.teal)
        var flags = window.decorView.systemUiVisibility
        flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        window.decorView.systemUiVisibility = flags
    }

}