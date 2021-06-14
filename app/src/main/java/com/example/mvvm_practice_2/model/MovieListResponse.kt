package com.example.mvvm_practice_2.model

import java.io.Serializable

class MovieListResponse: Serializable {
    var page: Int? = 1
    var results: List<Movie>? = null
    var total_pages: Int? = 0
    var total_results: Int? = 0
}