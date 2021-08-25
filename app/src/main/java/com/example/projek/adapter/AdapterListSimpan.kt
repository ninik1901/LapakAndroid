package com.example.projek.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projek.BacaEbook
import com.example.projek.databinding.ActivityCardSimpanBinding
import com.example.projek.model.ModelResponseSimpan

/**
 */
class AdapterListSimpan(
    private val dataset: List<ModelResponseSimpan.ModelSimpan>,
    private val context: Context
) : RecyclerView.Adapter<AdapterListSimpan.ViewHolder>() {
    class ViewHolder(view: ActivityCardSimpanBinding) : RecyclerView.ViewHolder(view.root) {
        var binding: ActivityCardSimpanBinding = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ActivityCardSimpanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.tvItemNama.text = dataset[position].judul_buku
        if (dataset[position].foto_cover != null) {
            Glide.with(context)
                .load("https://ninik.panjisastra.my.id/img/buku/${dataset[position].foto_cover}")
                .fitCenter()
                .dontAnimate()
                .into(holder.binding.image)
        }
        holder.itemView.setOnClickListener {
            val i = Intent(context, BacaEbook::class.java)
            i.putExtra("simpan", dataset[position])
            context.startActivity(i)
        }
    }
}