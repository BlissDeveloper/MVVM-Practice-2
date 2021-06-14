package com.example.mvvm_practice_2.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_practice_2.model.Movie
import com.example.mvvm_practice_2.model.MovieListResponse
import com.example.mvvm_practice_2.network.MovieEndpoints
import com.example.mvvm_practice_2.network.MovieRetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    private val movieList = MutableLiveData<MovieListResponse>()

    private val movieRetroInstance = MovieRetroInstance.getInstance()

    companion object {
        val TAG = "MovieRepository"
    }

    fun getMovies(page: Int) {
        val queryMap = mutableMapOf<String, String>()
        queryMap["api_key"] = MovieEndpoints.API_KEY
        queryMap["page"] = page.toString()
        movieRetroInstance.getMovieList(queryMap).enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                if (response.isSuccessful && response.code() == 200) {
                    movieList.postValue(response.body())
                } else {
                    movieList.postValue(null)
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                movieList.postValue(null)
            }
        })
    }


    fun getMovieListLiveData(): MutableLiveData<MovieListResponse> {
        return movieList
    }
}