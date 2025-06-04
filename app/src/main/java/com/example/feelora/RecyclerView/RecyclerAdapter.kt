package com.example.feelora.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feelora.R
import com.squareup.picasso.Picasso

class RecyclerAdapter(
    private var mList: List<RecyclerModel>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    fun updateData(newItems: List<RecyclerModel>){
        mList = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_card_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val item = mList[position]
        Picasso.get().load(item.imageUrl).into(holder.imageView)
        holder.textName.text = item.description
        println("Loading Items at position $position")
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) :RecyclerView.ViewHolder(ItemView){
        val textName: TextView = itemView.findViewById(R.id.textName)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}