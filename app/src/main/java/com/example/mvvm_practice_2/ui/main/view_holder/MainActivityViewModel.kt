package com.example.mvvm_practice_2.ui.main.view_holder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_practice_2.base.BaseViewModel
import com.example.mvvm_practice_2.model.Movie
import com.example.mvvm_practice_2.model.MovieListResponse
import com.example.mvvm_practice_2.repository.MovieRepository

class MainActivityViewModel : BaseViewModel() {
    private var movieList = MutableLiveData<MovieListResponse>()

    private val movieRepo = MovieRepository()

    private var page = 1

    companion object {
        val TAG = "MainActivityViewModel"
    }

    init {
        loadingBool.value = true
        movieRepo.getMovies(page)
        movieList = movieRepo.getMovieListLiveData()
    }

    fun getMovieList(): LiveData<MovieListResponse> {
        loadingBool.value = false
        return movieList
    }

    fun getMoreMovieList() {
        loadingBool.value = true
        movieRepo.getMovies(page++)
    }
}