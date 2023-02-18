package com.example.news_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val newsList: List<Article>, private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val newsImageView: ImageView = itemView.findViewById(R.id.news_img)
        val newsTitleView: TextView = itemView.findViewById(R.id.news_title)
        val newsContentView: TextView = itemView.findViewById(R.id.news_content)
        val newsAuthorView: TextView = itemView.findViewById(R.id.news_author)
        val newsTimeView: TextView = itemView.findViewById(R.id.news_time)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_cards, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.newsTitleView.text = news.title
        holder.newsContentView.text = news.description
        holder.newsAuthorView.text = news.author
        holder.newsTimeView.text = news.publishedAt
        Glide.with(holder.itemView.context).load(news.urlToImage).placeholder(R.drawable.progress_animation).error(R.drawable.try_later).into(holder.newsImageView)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(news)
        }
    }

    interface ItemClickListener {
        fun onItemClick(item: Article)
    }
}
