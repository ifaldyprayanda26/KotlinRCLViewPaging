package com.apps.ifaldyprayanda.kotlinrclviewpaging.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akiniyalocts.pagingrecycler.PagingAdapter
import com.apps.ifaldyprayanda.kotlinrclviewpaging.R
import com.apps.ifaldyprayanda.kotlinrclviewpaging.ViewHolder.MyViewHolder
import com.squareup.picasso.Picasso
import java.lang.StringBuilder
import java.util.*

class MyAdapter(var stringList: MutableList<String>): PagingAdapter() {

    var random: Random

    init {
        random = Random()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val myViewHolder = holder as MyViewHolder
        myViewHolder.txt_title.text = StringBuilder(stringList[position]).toString()
        Picasso.get().load(StringBuilder("https://picsum.photos/600/150?random=").append(random.nextInt())
            .toString()).into(myViewHolder.img_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false))
    }
    override fun getPagingLayout(): Int {
        return R.layout.layout_item
    }

    override fun getPagingItemCount(): Int {
        return stringList.size
    }

    fun addNewItem(itemNum: Int)
    {
        val lastSize = stringList.size
        for (i in lastSize until lastSize + itemNum) stringList.add(StringBuilder("Item id #").append(i).toString())
        notifyItemInserted(lastSize)
    }
}