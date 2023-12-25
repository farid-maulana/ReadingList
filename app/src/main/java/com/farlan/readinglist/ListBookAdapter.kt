package com.farlan.readinglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farlan.readinglist.databinding.ItemRowBookBinding

class ListBookAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<ListBookAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowBookBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowBookBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listBook.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, description, author, cover) = listBook[position]
        holder.binding.tvItemTitle.text = title
        holder.binding.tvItemAuthor.text = author
        holder.binding.tvItemDescription.text = description
//        holder.binding.imgItemCover.setImageResource(cover.toInt())

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listBook[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Book)
    }
}
