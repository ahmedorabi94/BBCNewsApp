package com.example.bbcnewsapp.features.new_list.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.bbcnewsapp.R
import com.example.bbcnewsapp.core.data.Resource
import com.example.bbcnewsapp.databinding.FragmentNewsListBinding
import com.example.bbcnewsapp.features.new_list.viewmodel.GetNewsListViewModel
import com.example.bbcnewsapp.features.news_details.ARTICLE_ARG_PARAM1
import com.example.bbcnewsapp.features.util.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GetNewsListViewModel by viewModels()


    private val adapter = NewsAdapter { article ->

        val bundle = Bundle()
        bundle.putParcelable(ARTICLE_ARG_PARAM1, article)
        findNavController().navigate(R.id.action_newsListFragment_to_newsDetailsFragment,bundle)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Top headlines"
        EspressoIdlingResource.increment()
        observeViewModel()
    }


    private fun observeViewModel() {
        viewModel.newsResponse.observe(viewLifecycleOwner) { response ->

            when (response.status) {
                Resource.Status.LOADING -> {
                    showLoading()
                }
                Resource.Status.SUCCESS -> {
                    hideLoading()
                    EspressoIdlingResource.decrement()
                    binding.recyclerViewMain.adapter = adapter
                    adapter.submitList(response.data)
                }
                Resource.Status.ERROR -> {
                   hideLoading()
                    Toast.makeText(activity, response.message, Toast.LENGTH_LONG).show()
                }

            }


        }

    }

    private fun showLoading() {
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressbar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}