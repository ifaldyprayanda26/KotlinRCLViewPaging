package com.apps.ifaldyprayanda.kotlinrclviewpaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akiniyalocts.pagingrecycler.PagingDelegate
import com.apps.ifaldyprayanda.kotlinrclviewpaging.Adapter.MyAdapter
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), PagingDelegate.OnPageListener {

    private val MAX_ITEM: Int = 25
    lateinit var adapter: MyAdapter

    lateinit var recycler_view: RecyclerView
    lateinit var cardview_progress_bar: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view = findViewById(R.id.recycler_view) as RecyclerView
        cardview_progress_bar = findViewById(R.id.card_view_progress) as CardView

        val layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recycler_view.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        generateData()
    }

    private fun generateData() {
        val stringList: MutableList<String> = ArrayList()
        for (i in 0..4) stringList.add(StringBuilder("Item id #").append(i).toString())
        adapter = MyAdapter(stringList)
        val pagingDelete = PagingDelegate.Builder(adapter)
            .attachTo(recycler_view)
            .listenWith(this)
            .build()

        recycler_view.adapter = adapter
    }

    override fun onDonePaging() {
        cardview_progress_bar.visibility = View.GONE
        Toast.makeText(this, "Max Loading !!!", Toast.LENGTH_LONG).show()
    }

    override fun onPage(sumOfItem: Int) {
        // show progress bar
        cardview_progress_bar.visibility = View.VISIBLE

        if (sumOfItem < MAX_ITEM)
        {
            //Fake Load Data
            Handler(Looper.myLooper())
                .postDelayed({
                    val lastSize = adapter!!.pagingItemCount - 1
                    cardview_progress_bar.visibility = View.GONE
                    adapter.addNewItem(5) // add more 5 page
                    recycler_view.smoothScrollToPosition(lastSize+3) // move recycler view
                }, 1500)
        }else
        {
            onDonePaging()
        }
    }
}