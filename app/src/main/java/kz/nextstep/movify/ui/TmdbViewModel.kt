package kz.nextstep.movify.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kz.nextstep.movify.api.ApiFactory
import kz.nextstep.movify.api.MovieRepository
import kz.nextstep.movify.data.TmdbMovie
import kotlin.coroutines.CoroutineContext

class TmdbViewModel : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.IO

    private val scope = CoroutineScope(coroutineContext)

    private val repository: MovieRepository =
        MovieRepository(ApiFactory.tmdbApi)


    val popularMoviesLiveData = MutableLiveData<MutableList<TmdbMovie>>()
    val currentMovieLiveData = MutableLiveData<TmdbMovie>()

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


    fun fetchMovie(id: Int) {
        scope.launch {
            val topRatedMovies = repository.getMovie(id)
            currentMovieLiveData.postValue(topRatedMovies)
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }


    fun cancelAllRequests() = coroutineContext.cancel()
}