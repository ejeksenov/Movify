package kz.nextstep.movify.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kz.nextstep.movify.R

class MainActivity : AppCompatActivity() {

    private lateinit var tmdbViewModel: TmdbViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tmdbViewModel = ViewModelProviders.of(this).get(TmdbViewModel::class.java)

        tmdbViewModel.fetchMovies()

        initRecyclerView()

        tmdbViewModel.popularMoviesLiveData.observe(this, Observer {
            val adapter = MovieAdapter(this, it)
            rv_main_list.adapter = adapter
        })

    }

    private fun initRecyclerView() {
        rv_main_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL ,false)
        rv_main_list.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
    }

    override fun onDestroy() {
        super.onDestroy()
        tmdbViewModel.cancelAllRequests()
    }
}

