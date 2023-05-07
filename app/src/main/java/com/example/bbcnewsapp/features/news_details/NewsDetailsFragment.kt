package com.example.bbcnewsapp.features.news_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.bbcnewsapp.core.domain.models.Article
import com.example.bbcnewsapp.databinding.FragmentNewsDetailsBinding
import com.example.bbcnewsapp.features.util.parcelable

const val ARTICLE_ARG_PARAM1 = "article_item"

class NewsDetailsFragment : Fragment() {


    private var article: Article? = null
    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article =   it.parcelable(ARTICLE_ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Headline Details"
        displayDetails(article)

    }


    private fun displayDetails(article: Article?){
        article?.let {
            binding.titleTV.text = article.title
            binding.descriptionTV.text = article.description ?: "No Description"
            binding.publishTv.text = article.publishedAt
            binding.contentTV.text = article.content ?: "No Content"
            binding.authorTV.text = article.author ?: "No Author"


            Glide.with(binding.root).load(article.urlToImage).into(binding.newsIV)
        }
    }

}