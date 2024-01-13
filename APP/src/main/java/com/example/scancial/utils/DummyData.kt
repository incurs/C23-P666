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
            bgColor = ""
        ),
        Tips(
            name = "Manfaat Merawat Wajah Pada Kehidupan Sehari-Hari",
            bgColor = ""
        ),
    )
    fun Product() = listOf(
        Product(
            imageUrl = "https://static.vecteezy.com/system/resources/previews/011/286/761/original/3d-cosmetic-serum-bottle-free-png.png",
            name = "Scancial Serum",
            price = "Rp120.000",
            rating = 4.8f,
            sell = "124 Terjual",
            deskripsi = ""
        ),
        Product(
            imageUrl = "https://images.somethinc.com/uploads/products/thumbs/500x500/Acnedot_Treatment_-_Moisturizer_Gel.png",
            name = "Scancial Pelembab",
            price = "Rp110.000",
            rating = 4.8f,
            sell = "257 Terjual",
            deskripsi = ""
        ),
        Product(
            imageUrl = "https://www.cetaphil.co.id/on/demandware.static/-/Sites-galderma-id-m-catalog/default/dwc290ac03/Ultra_Gentle_Body_Wash/Cetaphil%20Ultra%20Gentle%20Body%20Wash.png",
            name = "Cataphil",
            price = "Rp20.000",
            rating = 4.8f,
            sell = "768 Terjual",
            deskripsi = ""

        ),
        Product(
            imageUrl = "https://shopee.co.id/inspirasi-shopee/wp-content/uploads/2018/09/5a26200cb9830397675162-1024x724.png",
            name = "Scancial Paket 1",
            price = "Rp20.000",
            rating = 4.8f,
            sell = "768 Terjual",
            deskripsi = ""
        ),
        Product(
            imageUrl = "https://img.my-best.id/contents/54466b1f8ae7cf26c33ac8106001c0bc.png?ixlib=rails-4.3.1&q=70&lossless=0&w=1200&h=900&fit=crop&s=e5084651a6ed8e1f8ec5833c6920c553",
            name = "Scancial Siang Malam",
            price = "Rp20.000",
            rating = 4.8f,
            sell = "768 Terjual",
            deskripsi = ""
        ),
        Product(
            imageUrl = "https://sp-ao.shortpixel.ai/client/to_auto,q_glossy,ret_img,w_300,h_225/https://mpmbeauty.co.id/wp-content/uploads/2021/06/produk-barang-01-300x225.png",
            name = "Paket Lengkap Glowing",
            price = "Rp20.000",
            rating = 4.8f,
            sell = "768 Terjual",
            deskripsi = ""
        ),
        Product(
            imageUrl = "https://img.my-best.id/product_images/e21649177e8705d4dbed8715ec928b92.png?ixlib=rails-4.3.1&q=70&lossless=0&w=800&h=800&fit=clip&s=d4c0daddfcc4929c309cc4228a4fe8e5",
            name = "Snail Shampo",
            price = "Rp20.000",
            rating = 4.8f,
            sell = "768 Terjual",
            deskripsi = ""
        ),
        Product(
            imageUrl = "https://editorial.femaledaily.com/wp-content/uploads/2022/08/PRODUK-SKINCARE-RETINOL-HARGA-TERJANGKAU-HADA-LABO-GOKUJYUN-ALPHA-ULTIMATE-ANTI-AGING-LOTION.png",
            name = "Skincare Hadalabo",
            price = "Rp20.000",
            rating = 4.8f,
            sell = "768 Terjual",
            deskripsi = ""
        ),
    )
}