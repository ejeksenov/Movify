package kz.nextstep.movify.ui.movieDetailedInfo

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kz.nextstep.movify.api.MovieRepository
import kz.nextstep.movify.base.BaseViewModel
import kz.nextstep.movify.data.TmdbMovie
import kz.nextstep.movify.ui.movies.MovieViewModel
import javax.inject.Inject

class MovieDetailedViewModel : BaseViewModel() {
    @Inject
    lateinit var scope: CoroutineScope

    @Inject
    lateinit var repository: MovieRepository

    val currentMovieLiveData = MutableLiveData<TmdbMovie>()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val movieProductionCompaniesAdapter: MovieProductionCompaniesAdapter = MovieProductionCompaniesAdapter()

    private lateinit var observer: Observer<TmdbMovie>

    val movieViewModel: MovieViewModel = MovieViewModel()

    init {
        loadMovie()
    }

    private fun loadMovie() {
        loadingVisibility.value = View.VISIBLE

        observer = Observer {
            movieViewModel.bind(it)
            it.production_companies?.let { it1 -> movieProductionCompaniesAdapter.updateMovieProductionCompaniesList(it1) }
            loadingVisibility.value = View.GONE
        }
        currentMovieLiveData.observeForever(observer)
    }


    fun fetchMovie(id: Int) {
        scope.launch {
            val topRatedMovies = repository.getMovie(id)
            currentMovieLiveData.postValue(topRatedMovies)
        }
    }

    override fun onCleared() {
        currentMovieLiveData.removeObserver(observer)
        super.onCleared()
        cancelAllRequests()
    }


    fun cancelAllRequests() = scope.coroutineContext.cancel()


}