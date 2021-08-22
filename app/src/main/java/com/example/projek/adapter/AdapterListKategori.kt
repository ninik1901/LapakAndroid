package com.example.projek.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projek.R
import com.example.projek.databinding.ActivityCardKategoriBinding
import com.example.projek.model.ResponseKategori

/**
 */
class AdapterListKategori(
    private val dataset: List<ResponseKategori.ModelKategori>,
    private val context: Context
) : RecyclerView.Adapter<AdapterListKategori.ViewHolder>() {
    class ViewHolder(view: ActivityCardKategoriBinding) : RecyclerView.ViewHolder(view.root) {
        var binding: ActivityCardKategoriBinding = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ActivityCardKategoriBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = dataset.size

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.judulKategori.text = dataset[position].nama_kategori
        if (dataset[position].nama_kategori.equals("novel")) {
            holder.binding.imageKategori.setImageDrawable(context.getDrawable(R.drawable.novel))
        }
    }
}