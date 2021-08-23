package com.example.projek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
    }
}