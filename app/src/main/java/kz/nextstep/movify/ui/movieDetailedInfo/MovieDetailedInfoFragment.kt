package kz.nextstep.movify.ui.movieDetailedInfo


import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detailed_info.*

import kz.nextstep.movify.R
import kz.nextstep.movify.utils.AppConstants
import android.view.ViewTreeObserver
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_movie_detailed_info.view.*
import kz.nextstep.movify.utils.ChangeDateFormat
import kz.nextstep.movify.databinding.FragmentMovieDetailedInfoBinding
import kz.nextstep.movify.ui.movies.MovieViewModel


class MovieDetailedInfoFragment : Fragment() {

    private lateinit var tmdbViewModel: MovieDetailedViewModel

    private lateinit var binding: FragmentMovieDetailedInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detailed_info, container, false)
        tmdbViewModel = ViewModelProviders.of(this).get(MovieDetailedViewModel::class.java)

        val movieId = arguments?.getInt(AppConstants.MOVIE_ID_ARG) ?: 0

        tmdbViewModel.fetchMovie(movieId)

        binding.rvDetailedInfoProductionCompanies.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvDetailedInfoProductionCompanies.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))

        binding.svDetailedInfoScroll.viewTreeObserver.addOnScrollChangedListener(ScrollPositionObserver())

        binding.btnDetailedInfoBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.viewModel = tmdbViewModel

        return binding.root
    }

    private inner class ScrollPositionObserver : ViewTreeObserver.OnScrollChangedListener {

        private val mImageViewHeight: Int = resources.getDimensionPixelSize(R.dimen.movie_photo_height)

        override fun onScrollChanged() {
            val scrollY = Math.min(Math.max(binding.svDetailedInfoScroll.scrollY, 0), mImageViewHeight)

            // changing position of ImageView
            binding.ivDetailedInfoMoviePoster.translationY = (scrollY / 2).toFloat()

            // alpha you could set to ActionBar background
            val alpha = scrollY / mImageViewHeight.toFloat()

            binding.rlDetailedInfoTopLayout.alpha = alpha
            context?.resources?.getColor(R.color.colorPrimary)?.let { binding.rlDetailedInfoTopLayout.setBackgroundColor(it) }
            binding.tvDetailedInfoTitle.visibility = View.VISIBLE

        }
    }

}
