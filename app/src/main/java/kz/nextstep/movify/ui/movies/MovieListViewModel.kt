package kz.nextstep.movify.ui.movies

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.navigation.Navigation
import kotlinx.coroutines.*
import kz.nextstep.movify.api.MovieRepository
import kz.nextstep.movify.base.BaseViewModel
import kz.nextstep.movify.data.TmdbMovie
import kz.nextstep.movify.utils.AppConstants
import javax.inject.Inject

class MovieListViewModel: BaseViewModel() {

    @Inject lateinit var scope: CoroutineScope

    @Inject lateinit var repository: MovieRepository

    val popularMoviesLiveData = MutableLiveData<MutableList<TmdbMovie>>()
    val moviesListAdapter: MovieAdapter = MovieAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private lateinit var observer: Observer<MutableList<TmdbMovie>>

    init {
        loadMovies()
    }

    private fun loadMovies() {
        loadingVisibility.value = View.VISIBLE
        observer = Observer {
            moviesListAdapter.updateMovieList(it)
            loadingVisibility.value = View.GONE }
        popularMoviesLiveData.observeForever(observer)
    }

    fun fetchPopularMovies() {
        scope.launch {
            val popularMovies = repository.getPopularMovies()
            popularMoviesLiveData.postValue(popularMovies)
        }
    }

    fun fetchTopRatedMovies() {
        scope.launch {
            val topRatedMovies = repository.getTopRatedMovies()
            popularMoviesLiveData.postValue(topRatedMovies)
        }
    }

    override fun onCleared() {
        popularMoviesLiveData.removeObserver(observer)
        super.onCleared()
        cancelAllRequests()
    }


    fun cancelAllRequests() = scope.coroutineContext.cancel()
}