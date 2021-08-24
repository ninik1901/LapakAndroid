package com.example.projek.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.projek.BacaEbook
import com.example.projek.databinding.ItemImageSliderBinding
import com.example.projek.model.ResponModelEbook
import com.smarteist.autoimageslider.SliderViewAdapter


/**
 * Created by Dhimas Panji Sastra on
 * Copyright (c)  . All rights reserved.
 * Last modified $file.lastModified
 * Made With ❤ for U
 */

class AdapterImageSlider(
    context: Context,
    itemimage: ArrayList<ResponModelEbook.ModelEbook>
) :
    SliderViewAdapter<AdapterImageSlider.SliderHolder>() {
    var itemimage: ArrayList<ResponModelEbook.ModelEbook> = itemimage
    var context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup): SliderHolder {
        return SliderHolder(
            ItemImageSliderBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        viewHolder: SliderHolder,
        position: Int
    ) {
        val item: ArrayList<ResponModelEbook.ModelEbook> = itemimage
        Glide.with(context)
            .load("https://ninik.panjisastra.my.id/img/buku/${item[position].foto_cover}")
            .fitCenter()
            .dontAnimate()
            .into(viewHolder.binding.itemSlider)
        viewHolder.itemView.setOnClickListener {
            val i = Intent(context, BacaEbook::class.java)
            i.putExtra("detail", item[position])
            context.startActivity(i)
        }
    }

    override fun getCount(): Int {
        return itemimage.size
    }

    inner class SliderHolder(itemView: ItemImageSliderBinding) :
        ViewHolder(itemView.root) {
        var binding: ItemImageSliderBinding = itemView

    }

}