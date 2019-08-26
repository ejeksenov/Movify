package kz.nextstep.movify.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kz.nextstep.movify.utils.AppConstants
import kz.nextstep.movify.R
import kz.nextstep.movify.data.TmdbMovie
import kz.nextstep.movify.utils.ChangeDateFormat
import java.text.SimpleDateFormat
import java.util.*

class MovieAdapter(
    context: Context,
    val tmdbMovieList: MutableList<TmdbMovie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var onItemClick: ((TmdbMovie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View = inflater.inflate(R.layout.layout_raw_movie_list, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tmdbMovieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val tmdbMovie: TmdbMovie = tmdbMovieList[position]
        Picasso.get().load(AppConstants.TMDB_POSTER_BASE_URL + tmdbMovie.poster_path).into(holder.iv_raw_movie_image)
        holder.tv_raw_movie_title.text = tmdbMovie.title
        holder.tv_raw_movie_vote_average.text = tmdbMovie.vote_average.toString()
        holder.tv_raw_movie_overview.text = tmdbMovie.overview


        val releaseDate: String = ChangeDateFormat.onChangeDateFormat(tmdbMovie.release_date)
        holder.tv_raw_movie_release_date.text = releaseDate
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_raw_movie_image = itemView.findViewById(R.id.iv_raw_movie_image) as ImageView
        var tv_raw_movie_title = itemView.findViewById(R.id.tv_raw_movie_title) as TextView
        var tv_raw_movie_release_date = itemView.findViewById(R.id.tv_raw_movie_release_date) as TextView
        var tv_raw_movie_vote_average = itemView.findViewById(R.id.tv_raw_movie_vote_average) as TextView
        var tv_raw_movie_overview = itemView.findViewById(R.id.tv_raw_movie_overview) as TextView

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(tmdbMovieList[adapterPosition])
            }
        }
    }
}