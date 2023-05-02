package com.example.news_app

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomePage : AppCompatActivity(), NewsAdapter.ItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        val mSnapHelper: SnapHelper = PagerSnapHelper()
        mSnapHelper.attachToRecyclerView(recyclerview)

        getFilmData { data : List<Article> ->
            recyclerview.adapter = NewsAdapter(data, this)
        }
    }
    private fun getFilmData(callback: (List<Article>) -> Unit){
        val apiService = RetrofitClient.getRetrofitClient().create(ApiInterface::class.java)
        val call = apiService.getNewsList()
        call.enqueue(object: Callback<NewsResponse> {
//            override fun onResponse(call: Call<FilmRes>, response: Response<FilmRes>) {
//                if(response.isSuccessful){
//                    Log.i("Got data", response.body()!!.results[0].title)
//                }
//            }

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                return callback(response.body()!!.articles)
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

        })
    }


    override fun onItemClick(item: Article) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

    override fun onBackPressed() {
        return
    }
}
