package com.example.news_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper


class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)

//        val compSnapHelper: SnapHelper = LinearSnapHelper()
//        compSnapHelper.attachToRecyclerView(recyclerview)

        val mSnapHelper: SnapHelper = PagerSnapHelper()
        mSnapHelper.attachToRecyclerView(recyclerview)

        val data = ArrayList<News>()

        // This loop will create 20 Views containing

        // the image with the count of view
        for (i in 1..20) {
            data.add(News(R.drawable.samplepic, "FTX bankruptcy reveals Tom Brady as one of company’s biggest shareholders - NBC Sports", "Tom Brady‘s connection to FTX has resulted in both civil liability and the implosion of his massive equity stake in the company.Via FrontOfficeSports.com, and as originally reported by the New York Post, court documents filed in connection with the FTX bankru…", "Mike Florio", "2023-01-12T14:42:00Zl"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = NewsAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }
}