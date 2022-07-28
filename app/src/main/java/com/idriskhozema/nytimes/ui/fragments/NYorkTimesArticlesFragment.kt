package com.idriskhozema.nytimes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.idriskhozema.nytimes.R
import com.idriskhozema.nytimes.data.NyTimesData
import com.idriskhozema.nytimes.mvvm.NyTimesViewModel
import com.idriskhozema.nytimes.ui.adapters.NYorkTimesArticleAdapter
import com.idriskhozema.nytimes.utils.DataState
import kotlinx.android.synthetic.main.fragment_nyork_times_articles.*
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.idriskhozema.nytimes.data.Record
import com.idriskhozema.nytimes.ui.activities.LaunchActivity
import com.idriskhozema.nytimes.utils.AppConstants.SHARED_DATA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NYorkTimesArticlesFragment : Fragment() {

    internal val viewModel: NyTimesViewModel by activityViewModels()
    private var adapter : NYorkTimesArticleAdapter? = null
    var nyTimesData : NyTimesData? = null
    var layoutView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        if (layoutView == null)
            layoutView = inflater.inflate(R.layout.fragment_nyork_times_articles, container, false)
        return layoutView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (nyTimesData == null) {
            bindViews()
            getArticles()
            subscribeObserver()
        }
    }


    private fun bindViews() {

        //Set Title
        (activity as LaunchActivity).setToolbar(getString(R.string.new_york_times))

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun getArticles() {

        viewModel.getArticles()
    }

    private fun subscribeObserver() {

        viewModel.liveData.observe(viewLifecycleOwner,{ dataState->

            when(dataState) {

                DataState.Loading -> {
                   progressBar.visibility = View.VISIBLE

                }

                is DataState.Success -> {
                    progressBar.visibility = View.GONE
                    val response = dataState.data
                    response?.let { data->
                        processResponse(data)
                    }

                }
                is DataState.Error -> {
                    progressBar.visibility = View.GONE
                    showAlert(dataState.message)

                }
                else -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun showAlert(message: String) {
        MaterialAlertDialogBuilder(activity as LaunchActivity)
            .setMessage(message)
            .setNegativeButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun processResponse(data: NyTimesData) {
        nyTimesData = data
        nyTimesData?.let { response->
            adapter = NYorkTimesArticleAdapter(response,requireContext())
            recyclerView.adapter = adapter
            registerItemClickListener()
        }

    }

    private fun registerItemClickListener() {
        adapter?.onItemClick = object :
            NYorkTimesArticleAdapter.OnItemClickListener {
            override fun onItemClick(item: Record) {

                val bundle = bundleOf(SHARED_DATA to item)
                findNavController()
                    .navigate(
                        R.id.action_nyTimesArticles_to_articlesDetailsFragment,
                        bundle
                    )

            }
        }
    }


}