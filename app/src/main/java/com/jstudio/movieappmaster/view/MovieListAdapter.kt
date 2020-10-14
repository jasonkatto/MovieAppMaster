package com.jstudio.movieappmaster.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jstudio.movieappmaster.R
import com.jstudio.movieappmaster.model.movie.Result
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_movie.view.movie_name

class MovieListAdapter(val movieList: ArrayList<Result>) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {


    fun updateMoviesList(newMovieList: List<Result>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

    //viewHolder
    class MovieViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movie_name.text = movieList[position].title
        holder.view.movie_category.text = movieList[position].releaseDate
        holder.view.movie_homepage.text = movieList[position].originalLanguage
        Glide.with(holder.view).load(
            "https://image.tmdb.org/t/p/w500" + movieList[position]
                .posterPath
        ).into(holder.view.imageView)

        holder.view.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(
                    ListFragmentDirections.actionDetailFragment(
                        "https://image.tmdb.org/t/p/w500" + movieList[position]
                            .posterPath, movieList[position].overview
                    )
                )


        }
    }

    override fun getItemCount() = movieList.size
}