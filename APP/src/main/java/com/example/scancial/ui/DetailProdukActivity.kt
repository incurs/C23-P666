package com.example.scancial.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scancial.R
import com.example.scancial.databinding.ActivityDetailProdukBinding

class DetailProdukActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailProdukBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityDetailProdukBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}