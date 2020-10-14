package com.jstudio.movieappmaster.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.jstudio.movieappmaster.R
import com.jstudio.movieappmaster.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_movie.*
import kotlinx.android.synthetic.main.item_movie.movie_name
import kotlinx.android.synthetic.main.item_movie.view.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel

    private var movieImage: String? = null
    private var movieOver: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        arguments?.let {
            movieImage = DetailFragmentArgs.fromBundle(it).movieImage
            movieOver = DetailFragmentArgs.fromBundle(it).movieOverview

//            movie_name.text = movie.movieName
//            movieGenres.text = movie.movieCategory
            activity?.let { it1 ->
                Glide.with(it1).load(movieImage).into(movieImages) }
            movieOverview.text = movieOver

        }

//        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.movieLiveData.observe(this, Observer { movie ->
            movie?.let {
                movie_name.text = movie.movieName
                movieGenres.text = movie.movieCategory
                movieOverview.text = movie.movieOverview
            }
        })
    }


}