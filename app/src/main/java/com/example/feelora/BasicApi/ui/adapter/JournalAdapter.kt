package com.example.feelora.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feelora.BasicApi.data.model.JournalItems
import com.example.feelora.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class JournalAdapter(
    private var mList: List<JournalItems>, // Menggunakan JournalItems
    private val onDeleteClick: (String) -> Unit // Callback untuk hapus
) : RecyclerView.Adapter<JournalAdapter.ViewHolder>() {

    fun updateData(newItems: List<JournalItems>) {
        mList = newItems
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        // Memuat gambar menggunakan Picasso
        Picasso.get().load(item.imageUrl).into(holder.binding.imageView) // Pastikan imageUrl ada di JournalItems
        holder.binding.textName.text = item.title // Menggunakan title dari JournalItems
        holder.binding.textDesc.text = item.journal // Menggunakan deskripsi dari JournalItems

        // Menambahkan listener untuk tombol hapus
        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(item._uuid) // Memanggil callback dengan UUID dari JournalItems
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
