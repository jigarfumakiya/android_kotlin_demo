package com.app.android_test.features.home.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.android_test.core.utility.extension.inflater
import com.app.android_test.databinding.ItemNewsBinding
import com.app.android_test.domain.model.ArticleDomain
import com.bumptech.glide.Glide

/**
 * @Author: Jigar Fumakiya
 * @Date: 01/12/24
 */

class NewsAdapter(private val onItemTap: (airtcle: ArticleDomain) -> Unit = {}) :
    ListAdapter<ArticleDomain, NewsAdapter.NewsItemView>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemView {
        return NewsItemView(
            ItemNewsBinding.inflate(
                parent.context.inflater,
                parent,
                false
            ),
            onItemTap
        )
    }

    override fun onBindViewHolder(holder: NewsItemView, position: Int) {
        holder.bind(getItem(position))
    }


    inner class NewsItemView(
        private val itemBinding: ItemNewsBinding,
        private val onItemTap: (airtcle: ArticleDomain) -> Unit = {}
    ) : RecyclerView.ViewHolder(itemBinding.root) {


        fun bind(article: ArticleDomain) {
            with(itemBinding) {
                Glide.with(itemBinding.root.context)
                    .load(article.urlToImage)
                    .fitCenter()
                    .into(newsImageview)

                newsTitleTextview.text = article.title
                newsDescTextview.text = article.content
            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<ArticleDomain>() {
        override fun areItemsTheSame(
            oldItem: ArticleDomain,
            newItem: ArticleDomain,
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: ArticleDomain,
            newItem: ArticleDomain,
        ): Boolean {
            return oldItem == newItem
        }
    }


}