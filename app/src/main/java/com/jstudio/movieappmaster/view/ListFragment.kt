package com.jstudio.movieappmaster.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jstudio.movieappmaster.R
import com.jstudio.movieappmaster.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel
    private val moviesListAdapter = MovieListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        viewModel.refresh()

        moviesList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = moviesListAdapter
        }

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            moviesList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.movies.observe(this, Observer { movies ->
            movies?.let {
                moviesList.visibility = View.VISIBLE
                moviesListAdapter.updateMoviesList(movies)

            }
        })

        viewModel.moviesLoadError.observe(this, Observer { isError ->
            isError?.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    listError.visibility = View.GONE
                    moviesList.visibility = View.GONE
                }
            }
        })
    }
}