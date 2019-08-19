package kz.nextstep.movify.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kz.nextstep.movify.api.ApiFactory
import kz.nextstep.movify.api.MovieRepository
import kz.nextstep.movify.data.TmdbMovie
import kotlin.coroutines.CoroutineContext

class TmdbViewModel: ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : MovieRepository =
        MovieRepository(ApiFactory.tmdbApi)


    val popularMoviesLiveData = MutableLiveData<MutableList<TmdbMovie>>()

    fun fetchMovies(){
        scope.launch {
            val popularMovies = repository.getPopularMovies()
            popularMoviesLiveData.postValue(popularMovies)
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()
}