package com.jstudio.movieappmaster.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jstudio.movieappmaster.model.MovieInfo
import com.jstudio.movieappmaster.model.MoviesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MovieListViewModel : ViewModel() {

    private val moviesService = MoviesApiService()
    private val disposable = CompositeDisposable()


    val movies = MutableLiveData<List<MovieInfo>>()

    val moviesLoadError = MutableLiveData<Boolean>()


    val loading = MutableLiveData<Boolean>()


    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            moviesService.getMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<MovieInfo>>() {
                    override fun onSuccess(movieList: List<MovieInfo>) {
                        movies.value = movieList
                        moviesLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        moviesLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }


                })
        )


    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}