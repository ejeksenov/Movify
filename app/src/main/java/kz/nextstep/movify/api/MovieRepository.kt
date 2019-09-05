package kz.nextstep.movify.api

import kz.nextstep.movify.data.TmdbMovie
import javax.inject.Inject

class MovieRepository(val api: TmdbApi): BaseRepository() {


    suspend fun getPopularMovies() : MutableList<TmdbMovie>?{
        val movieResponse = safeApiCall(
            call = {api.getPopularMovie().await()},
            errorMessage = "Error Fetching Popular Movies"
        )

        return movieResponse?.results?.toMutableList()
    }

    suspend fun getTopRatedMovies() : MutableList<TmdbMovie>?{
        val movieResponse = safeApiCall(
            call = {api.getTopRatedMovie().await()},
            errorMessage = "Error Fetching Top Rated Movies"
        )

        return movieResponse?.results?.toMutableList()
    }


    suspend fun getMovie(id: Int) : TmdbMovie?{

        return safeApiCall(
        call = { api.getMovie(id).await()},
        errorMessage = "Error Fetching Current Movie by Id"
    )
    }
}