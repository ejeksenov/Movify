package kz.nextstep.movify.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.fragment_movie_list.view.*

import kz.nextstep.movify.R
import kz.nextstep.movify.utils.AppConstants


class MovieListFragment : Fragment() {

    private var mView: View? = null
    private lateinit var tmdbViewModel: TmdbViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_movie_list, container, false)

            tmdbViewModel = ViewModelProviders.of(this).get(TmdbViewModel::class.java)

            initRecyclerView(mView!!)

            tmdbViewModel.popularMoviesLiveData.observe(this, Observer {
                mView?.pb_movie_list_progress_bar?.visibility = View.GONE
                val adapter = MovieAdapter(context!!, it)
                mView?.rv_main_list?.adapter = adapter

                adapter.onItemClick = { tmdbMovie ->
                    val bundle = bundleOf(AppConstants.MOVIE_ID_ARG to tmdbMovie.id)
                    Navigation.findNavController(mView!!)
                        .navigate(R.id.action_movieListFragment_to_movieDetailedInfoFragment, bundle)
                }
            })

            mView?.btn_movie_list_popular?.setOnClickListener {
                mView?.pb_movie_list_progress_bar?.visibility = View.VISIBLE
                mView?.btn_movie_list_popular?.setTextColor(resources.getColor(R.color.white))
                mView?.btn_movie_list_top_rated?.setTextColor(resources.getColor(R.color.colorPrimary))
                tmdbViewModel.fetchPopularMovies()
            }

            mView?.btn_movie_list_top_rated?.setOnClickListener {
                mView?.pb_movie_list_progress_bar?.visibility = View.VISIBLE
                mView?.btn_movie_list_top_rated?.setTextColor(resources.getColor(R.color.white))
                mView?.btn_movie_list_popular?.setTextColor(resources.getColor(R.color.colorPrimary))
                tmdbViewModel.fetchTopRatedMovies()
            }

            mView?.btn_movie_list_popular?.performClick()
        }

        return mView
    }


    private fun initRecyclerView(view: View) {
        view.rv_main_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        view.rv_main_list.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
    }


}
