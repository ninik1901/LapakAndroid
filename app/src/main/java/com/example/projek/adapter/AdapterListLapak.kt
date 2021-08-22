package com.example.projek.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projek.databinding.ActivityCardJadwalBinding
import com.example.projek.model.ModeljadwalLapak

class AdapterListLapak(
    private val dataset: List<ModeljadwalLapak.Lapak>
) : RecyclerView.Adapter<AdapterListLapak.ViewHolder>() {
    class ViewHolder(view: ActivityCardJadwalBinding) : RecyclerView.ViewHolder(view.root) {
        var binding: ActivityCardJadwalBinding = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ActivityCardJadwalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvItemNama.text = dataset[position].nama_kegiatan
        holder.binding.tvItemLokasi.text = dataset[position].lokasi.toString()
        holder.binding.tvItemRelawan.text = dataset[position].jumlah_relawan.toString()
        holder.binding.tvItemTanggal.text = dataset[position].tanggal.toString()
    }
}