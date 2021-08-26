package com.example.projek.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projek.BacaEbook
import com.example.projek.databinding.ItemEbookBinding
import com.example.projek.model.ResponseKategoriEbook

/**
 */
class AdapterKategoriEbook(
    private val dataset: List<ResponseKategoriEbook.kategoriEbook>,
    private val context: Context
) : RecyclerView.Adapter<AdapterKategoriEbook.ViewHolder>() {
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
        holder.binding.judulBuku.text = dataset[position].judul_buku
        if (dataset[position].foto_cover != null) {
            Glide.with(context)
                .load("https://ta.poliwangi.ac.id/~ti18099/public/img/buku/${dataset[position].foto_cover}")
                .fitCenter()
                .dontAnimate()
                .into(holder.binding.imgEbook)
        }


        holder.itemView.setOnClickListener {
            val i = Intent(context, BacaEbook::class.java)
            i.putExtra("kategori", dataset[position])
            context.startActivity(i)
        }
    }
}