package kz.nextstep.movify.api

import kz.nextstep.movify.data.TmdbMovie

class MovieRepository(private val api: TmdbApi): BaseRepository() {
    suspend fun getPopularMovies() : MutableList<TmdbMovie>?{
        val movieResponse = safeApiCall(
            call = {api.getPopularMovie().await()},
            errorMessage = "Error Fetching Popular Movies"
        )

        return movieResponse?.results?.toMutableList()
    }
}