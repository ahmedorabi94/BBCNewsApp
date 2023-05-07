package com.example.bbcnewsapp.features.new_list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bbcnewsapp.core.domain.models.Article
import com.example.bbcnewsapp.databinding.NewsItemBinding

class NewsAdapter(private val callback: (article: Article) -> Unit) :
    ListAdapter<Article, NewsAdapter.MyViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Article,
            newItem: Article
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.cardViewItem.setOnClickListener {
            callback.invoke(item)
        }

    }


    class MyViewHolder(var binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article) {
            binding.titleTV.text = item.title
            binding.dateTv.text = item.publishedAt
            Glide.with(binding.root).load(item.urlToImage).into(binding.newsIV)
        }

    }


}