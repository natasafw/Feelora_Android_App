package com.example.feelora.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feelora.R
import com.example.feelora.databinding.HomeNewItemHorizontalBinding
import com.squareup.picasso.Picasso

class HomeNewsHorizontalAdapter(
    private var mList: List<HomeNewsHorizontalModel>
) : RecyclerView.Adapter<HomeNewsHorizontalAdapter.ViewHolder>(){

    fun updateData(newItems: List<HomeNewsHorizontalModel>){
        mList = newItems
        notifyDataSetChanged()
    }
    class ViewHolder(val binding: HomeNewItemHorizontalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_new_item_horizontal,parent,false)
//        return ViewHolder(view)

        val binding = HomeNewItemHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        Picasso.get().load(item.imageUrl).into(holder.binding.newsHoriImage)
        holder.binding.newsHoriTitle.text = item.menuName
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}