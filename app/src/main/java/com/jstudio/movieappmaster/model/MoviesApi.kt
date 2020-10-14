package com.jstudio.movieappmaster.model

import io.reactivex.Single
import retrofit2.http.GET

interface MoviesApi {

    @GET("3/movie/550?api_key=464f15d480e6f406f0e732d52706b2e0")
    fun getMovies(): Single<List<MovieInfo>>
}