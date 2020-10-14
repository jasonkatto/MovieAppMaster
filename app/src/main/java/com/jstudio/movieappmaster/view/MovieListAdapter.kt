package com.jstudio.movieappmaster.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jstudio.movieappmaster.R
import com.jstudio.movieappmaster.model.MovieInfo
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_movie.view.movie_name

class MovieListAdapter(val movieList: ArrayList<MovieInfo>) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {


    fun updateMoviesList(newMovieList: List<MovieInfo>) {
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
        holder.view.movie_name.text = movieList[position].movieName
        holder.view.movie_category.text = movieList[position].movieCategory
        holder.view.movie_homepage.text = movieList[position].movieHomepage

        holder.view.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment(5))


        }
    }

    override fun getItemCount() = movieList.size
}