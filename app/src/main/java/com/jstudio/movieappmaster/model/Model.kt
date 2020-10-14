package com.jstudio.movieappmaster.model

import com.google.gson.annotations.SerializedName

data class MovieInfo(

    @SerializedName("imdb_id")
    val movieId: String?,


    @SerializedName("original_title")
    val movieName: String?,

    @SerializedName("overview")
    val movieOverview: String,


    @SerializedName("homepage")
    val movieHomepage: String?,


    @SerializedName("genres")
    val movieCategory: String?,


    @SerializedName("poster_path")
    val moveUrl: String?

)