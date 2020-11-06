package com.apps.ifaldyprayanda.kotlinrclviewpaging.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.ifaldyprayanda.kotlinrclviewpaging.R

class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
     var img_thumbnail: ImageView
     var txt_title: TextView

    init {
        img_thumbnail = itemView.findViewById(R.id.img_thumbnail)
        txt_title = itemView.findViewById(R.id.txt_title)
    }
}