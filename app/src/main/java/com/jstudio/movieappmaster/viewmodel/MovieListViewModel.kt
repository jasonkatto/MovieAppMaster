package com.jstudio.movieappmaster.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jstudio.movieappmaster.model.MoviesApi
import com.jstudio.movieappmaster.model.MoviesApiService
import com.jstudio.movieappmaster.model.movie.Example
import com.jstudio.movieappmaster.model.movie.Result
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListViewModel : ViewModel() {

    private val moviesService = MoviesApiService()
    private val disposable = CompositeDisposable()


    val movies = MutableLiveData<List<Result>>()

    val moviesLoadError = MutableLiveData<Boolean>()


    val loading = MutableLiveData<Boolean>()


    fun refresh() {
        fetchFromRemote("8d2da06078efb1bd02b0e4e53c43e75b")
    }

    private fun fetchFromRemote(apiKey: String) {
        loading.value = true

        val apiService: MoviesApi = MoviesApiService.getClient()!!.create(MoviesApi::class.java)

        val call: Call<Example?>? = apiService.getMovies(apiKey)
        call!!.enqueue(object : Callback<Example?> {
            override fun onResponse(
                call: Call<Example?>?,
                response: Response<Example?>
            ) {
                val moviesList: Example? = response.body()
                movies.value = moviesList!!.results
                moviesLoadError.value = false
                loading.value = false
            }

            override fun onFailure(call: Call<Example?>?, t: Throwable) {
                // Log error here since request failed
                moviesLoadError.value = true
                loading.value = false
                t.printStackTrace()
            }
        })

//        disposable.add(
//            moviesService.getMovies(apiKey)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(object : DisposableSingleObserver<List<MovieInfo>>() {
//                    override fun onSuccess(movieList: List<MovieInfo>) {
//                        movies.value = movieList
//                        moviesLoadError.value = false
//                        loading.value = false
//                    }
//
//                    override fun onError(e: Throwable) {
//                        moviesLoadError.value = true
//                        loading.value = false
//                        e.printStackTrace()
//                    }
//
//
//                })
//        )


    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}