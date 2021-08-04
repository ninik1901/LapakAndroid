package com.example.projek.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projek.BacaEbook
import com.example.projek.databinding.ItemEbookBinding
import com.example.projek.model.ResponModelEbook

/**
 * Created by Dhimas Panji Sastra on
 * Copyright (c)  . All rights reserved.
 * Last modified $file.lastModified
 * Made With ❤ for U
 */
class AdapterListEbook(
    private val dataset: List<ResponModelEbook.ModelEbook>,
    private val context: Context
) : RecyclerView.Adapter<AdapterListEbook.ViewHolder>() {
    class ViewHolder(view: ItemEbookBinding) : RecyclerView.ViewHolder(view.root) {
        var binding: ItemEbookBinding = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEbookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.itemNamaPengarang.text = dataset[position].judul_buku
//        holder.binding.itemJumlahBuku.text = dataset[position].jumlah_buku.toString()
//        holder.binding.itemJumlahHalaman.text = dataset[position].jumlah_halaman.toString()
//        holder.binding.itemTahun.text = dataset[position].tahun_terbit
        holder.itemView.setOnClickListener {
            val i = Intent(context, BacaEbook::class.java)
            i.putExtra("detail", dataset[position])
            context.startActivity(i)
        }
    }
}