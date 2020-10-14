package com.jstudio.movieappmaster.model

import okhttp3.Dns
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MoviesApiService {
    val BASE_URL = "https://api.themoviedb.org/"
    private var retrofit: Retrofit? = null
//
//    private val BASE_URL = "https://api.themoviedb.org/"
//
//    private val api = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build()
//        .create(MoviesApi::class.java)


//    fun getMovies(): Single<List<MovieInfo>> {
//        return api.getMovies()
//    }

    companion object {
        val BASE_URL = "https://api.themoviedb.org/"
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(
                        OkHttpClient.Builder()
                            .dns(Dns.SYSTEM)
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .build()
                    )
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }


}