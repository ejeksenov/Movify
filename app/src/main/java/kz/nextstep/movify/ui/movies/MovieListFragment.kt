package kz.nextstep.movify.ui.movies


import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_movie_list.view.*

import kz.nextstep.movify.R
import kz.nextstep.movify.databinding.FragmentMovieListBinding
import kz.nextstep.movify.utils.AppConstants


class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var tmdbViewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)

        tmdbViewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)

        binding.rvMainList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvMainList.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))

        binding.btnMovieListPopular.setOnClickListener {
            binding.btnMovieListPopular.setTextColor(resources.getColor(R.color.white))
            binding.btnMovieListTopRated.setTextColor(resources.getColor(R.color.colorPrimary))
            tmdbViewModel.fetchPopularMovies()
        }

        binding.btnMovieListTopRated.setOnClickListener {
            binding.btnMovieListTopRated.setTextColor(resources.getColor(R.color.white))
            binding.btnMovieListPopular.setTextColor(resources.getColor(R.color.colorPrimary))
            tmdbViewModel.fetchTopRatedMovies()
        }

        binding.btnMovieListPopular.performClick()

        binding.viewModel = tmdbViewModel


        tmdbViewModel.moviesListAdapter.onItemClick = { tmdbMovie ->
            val bundle = bundleOf(AppConstants.MOVIE_ID_ARG to tmdbMovie.id)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_movieListFragment_to_movieDetailedInfoFragment, bundle)
        }

        return binding.root
    }


}