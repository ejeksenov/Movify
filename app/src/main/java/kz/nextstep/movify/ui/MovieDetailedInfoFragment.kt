package kz.nextstep.movify.ui


import android.os.Bundle
import android.text.Html
import android.util.DisplayMetrics
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_movie_detailed_info.view.*
import kz.nextstep.movify.utils.ChangeDateFormat
import java.text.SimpleDateFormat
import java.util.*




class MovieDetailedInfoFragment : Fragment() {

    private lateinit var tmdbViewModel: TmdbViewModel
    lateinit var sv_detailed_info_scroll: ScrollView
    lateinit var iv_detailed_info_movie_poster: ImageView
    lateinit var rl_detailed_info_top_layout: RelativeLayout
    lateinit var tv_detailed_info_title: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_movie_detailed_info, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sv_detailed_info_scroll = view.findViewById(R.id.sv_detailed_info_scroll)
        iv_detailed_info_movie_poster = view.findViewById(R.id.iv_detailed_info_movie_poster)
        rl_detailed_info_top_layout = view.findViewById(R.id.rl_detailed_info_top_layout)
        tv_detailed_info_title = view.findViewById(R.id.tv_detailed_info_title)

        pb_movie_detailed_info_progress_bar.visibility = View.VISIBLE

        val movieId = arguments?.getInt(AppConstants.MOVIE_ID_ARG) ?: 0
        tmdbViewModel = ViewModelProviders.of(this).get(TmdbViewModel::class.java)

        tmdbViewModel.fetchMovie(movieId)

        initRecyclerView(view)


        sv_detailed_info_scroll.viewTreeObserver.addOnScrollChangedListener(ScrollPositionObserver())

        tmdbViewModel.currentMovieLiveData.observe(this, Observer {
            pb_movie_detailed_info_progress_bar.visibility = View.GONE
            //Log.e("tmdbMovie", it.title)
            Picasso.get().load(AppConstants.TMDB_POSTER_BASE_URL + it.poster_path).into(iv_detailed_info_movie_poster)
            tv_detailed_info_title.text = it.title
            tv_detailed_info_title_large.text = it.title
            tv_detailed_info_rating.text = it.vote_average.toString()

            tv_detailed_info_overview.text = it.overview

            var countries = ""
            it.production_countries?.forEachIndexed { index, productionCountriesData ->
                var additionalComa = ", "
                if (index == it.production_countries.size.minus(1))
                    additionalComa = ""
                countries += productionCountriesData.name + additionalComa
            }
            var genres = "<b>Genre</b><br>"
            it.genres?.forEachIndexed { index, genresData ->
                var additionalComa = ", "
                if (index == it.genres.size.minus(1))
                    additionalComa = ""
                genres += genresData.name + additionalComa
            }
            tv_detailed_info_genre.text = Html.fromHtml(genres)

            //formatting date
            val releaseDate: String = ChangeDateFormat.onChangeDateFormat(it.release_date) + ", " + countries
            tv_detailed_info_year_country.text = releaseDate

            if (it.runtime != null) {
                val runtime = it?.runtime.toString() + " min"
                tv_detailed_info_runtime.text = runtime
            }

            var spokenLanguages = "<b>Languages</b><br>"
            it.spoken_languages?.forEachIndexed { index, spokenLanguagesData ->
                var additionalComa = ", "
                if (index == it.spoken_languages.size.minus(1))
                    additionalComa = ""
                spokenLanguages += spokenLanguagesData.name + additionalComa
            }
            tv_detailed_info_languages.text = Html.fromHtml(spokenLanguages)

            val adapter = it.production_companies?.let { it1 -> MovieProductionCompaniesAdapter(context!!, it1) }
            rv_detailed_info_production_companies?.adapter = adapter

        })


        btn_detailed_info_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initRecyclerView(view: View) {
        view.rv_detailed_info_production_companies.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        view.rv_detailed_info_production_companies.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
    }


    private inner class ScrollPositionObserver : ViewTreeObserver.OnScrollChangedListener {

        private val mImageViewHeight: Int = resources.getDimensionPixelSize(R.dimen.movie_photo_height)

        override fun onScrollChanged() {
            val scrollY = Math.min(Math.max(sv_detailed_info_scroll.scrollY, 0), mImageViewHeight)

            // changing position of ImageView
            iv_detailed_info_movie_poster.translationY = (scrollY / 2).toFloat()

            // alpha you could set to ActionBar background
            val alpha = scrollY / mImageViewHeight.toFloat()

            rl_detailed_info_top_layout.alpha = alpha
            context?.resources?.getColor(R.color.colorPrimary)?.let { rl_detailed_info_top_layout.setBackgroundColor(it) }
            tv_detailed_info_title.visibility = View.VISIBLE

        }
    }

}
