package com.jstudio.movieappmaster.model

import com.jstudio.movieappmaster.model.movie.Example
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("3/movie/popular")
    open fun getMovies(@Query("api_key") apiKey: String?): Call<Example?>?

//    fun getMovies(@Query("api_key") apiKey: String): Single<List<MovieInfo>>
}