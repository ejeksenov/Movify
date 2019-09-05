package kz.nextstep.movify.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kz.nextstep.movify.R
import kz.nextstep.movify.data.TmdbMovie
import kz.nextstep.movify.databinding.LayoutRawMovieListBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    lateinit var tmdbMovieList: MutableList<TmdbMovie>


    var onItemClick: ((TmdbMovie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: LayoutRawMovieListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_raw_movie_list, parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::tmdbMovieList.isInitialized) tmdbMovieList.size else 0
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(tmdbMovieList[position])
    }

    fun updateMovieList(tmdbMovieList: MutableList<TmdbMovie>){
        this.tmdbMovieList = tmdbMovieList
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: LayoutRawMovieListBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = MovieViewModel()

        fun bind(tmdbMovie: TmdbMovie) {
            viewModel.bind(tmdbMovie)
            binding.viewModel = viewModel
            binding.root.setOnClickListener {
                onItemClick?.invoke(tmdbMovie)
            }
        }

    }
}

