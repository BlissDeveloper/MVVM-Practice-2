package com.example.mvvm_practice_2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_practice_2.R
import com.example.mvvm_practice_2.model.Movie
import com.example.mvvm_practice_2.ui.main.adapter.MovieAdapter
import com.example.mvvm_practice_2.ui.main.view_holder.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var movieAdapter: MovieAdapter? = null
    private lateinit var mainActivityViewModel: MainActivityViewModel

    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.getMovieList().observe(this, Observer {
            pbMain.visibility = View.GONE
            if (it != null) {
                if (!it.results.isNullOrEmpty()) {
                    movieAdapter?.setData(it.results!!)
                }
            }
        })

        mainActivityViewModel.isLoading().observe(this, Observer {
            if (it) {
                pbMain.visibility = View.VISIBLE
            }
        })

        initRv()
    }

    private fun initRv() {
        movieAdapter = MovieAdapter()
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    mainActivityViewModel.getMoreMovieList()
                }
            }
        })
    }
}