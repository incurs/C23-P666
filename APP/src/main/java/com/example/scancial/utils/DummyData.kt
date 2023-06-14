package com.example.scancial.utils

import com.example.scancial.core.entity.Product
import com.example.scancial.core.entity.Tips

object DummyData {
    fun Image() = listOf(
        "https://st3.depositphotos.com/1715570/34470/i/450/depositphotos_344703004-stock-photo-close-selfie-portrait-smiling-north.jpg"
    )
    fun Tips() = listOf(
        Tips(
            name = "Tips Merawat Wajah dari Bahan Alami",
            bgColor = ""
        ),
        Tips(
            name = "Skincare Rutin untuk Pemula",
            bgColor = "#0C8173"
        ),
    )
    fun Product() = listOf(
        Product(
            imageUrl = "https://static.vecteezy.com/system/resources/previews/011/286/761/original/3d-cosmetic-serum-bottle-free-png.png",
            name = "Scancial Serum",
            price = "Rp120.000",
            rating = 4.8f,
            sell = "124 Terjual"
        ),
        Product(
            imageUrl = "https://static.vecteezy.com/system/resources/previews/011/286/761/original/3d-cosmetic-serum-bottle-free-png.png",
            name = "Scancial Pelembab",
            price = "Rp110.000",
            rating = 4.8f,
            sell = "257 Terjual"
        ),
        Product(
            imageUrl = "https://static.vecteezy.com/system/resources/previews/011/286/761/original/3d-cosmetic-serum-bottle-free-png.png",
            name = "Scancial Wash",
            price = "Rp20.000",
            rating = 4.8f,
            sell = "768 Terjual"
        ),
        Product(
            imageUrl = "https://static.vecteezy.com/system/resources/previews/011/286/761/original/3d-cosmetic-serum-bottle-free-png.png",
            name = "Scancial Wash",
            price = "Rp20.000",
            rating = 4.8f,
            sell = "768 Terjual"
        ),
    )
}