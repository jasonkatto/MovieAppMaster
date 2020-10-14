package com.jstudio.movieappmaster.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jstudio.movieappmaster.model.MovieInfo

class DetailViewModel : ViewModel() {

    val movieLiveData = MutableLiveData<MovieInfo>()

    fun fetch(uuid: Int) {
        val movie = MovieInfo("1", "ddgd", "dgdg", "fdgd", "dfdg", "dd")
        movieLiveData.value = movie
    }

}