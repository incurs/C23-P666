package com.example.scancial.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scancial.core.entity.Product
import com.example.scancial.databinding.ItemProductBinding

class ProductAdapter(private val callback: ProductClickCallback) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private val products = ArrayList<Product>()

    fun setData(items: List<Product>) {
        products.clear()
        products.addAll(items)
        notifyDataSetChanged()
    }

    interface ProductClickCallback {
        fun onClickProduct(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            with(binding) {
                Glide.with(itemView)
                    .load(item.imageUrl)
                    .into(imgProduct)
                tvTitle.text = item.name
                tvPrize.text = item.price.toString()
                tvSell.text = item.sell.toString()
                tvRating.text = item.rating.toString()

                root.setOnClickListener { callback.onClickProduct(item) }
            }
        }
    }
}
