package dev.agustacandi.learn.pestsentry.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.agustacandi.learn.pestsentry.databinding.ItemImageBinding

class ImageAdapter(private val listImage: ArrayList<Int>) : RecyclerView.Adapter<ImageAdapter.ListViewHolder>() {
    class ListViewHolder(val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listImage.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val image = listImage[position]
        holder.binding.itemImage.load(image)
    }
}