package com.example.myapplication.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.Content
import com.example.myapplication.databinding.ItemListRawBinding

class ListReleaseDocAdapter(private val onItemClick: (Content) -> Unit) :
    RecyclerView.Adapter<ListReleaseDocAdapter.MyViewHolder>() {

    private val data: ArrayList<Content> = ArrayList()


    inner class MyViewHolder(val binding: ItemListRawBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: Content) {
            with(item) {
                binding.tvTitle.text = mediaTitleCustom
                binding.tvTitle.setOnClickListener {
                    onItemClick(this)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemListRawBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAllItem(list: ArrayList<Content>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }
}
