package com.example.mvvm_practice_2.network

import com.example.mvvm_practice_2.model.Movie
import com.example.mvvm_practice_2.model.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieService {
    @GET(MovieEndpoints.MOVIE_LIST)
    fun getMovieList(
        @QueryMap queryMap: Map<String, String>
    ): Call<MovieListResponse>
}