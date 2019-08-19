package kz.nextstep.movify.api

import kotlinx.coroutines.Deferred
import kz.nextstep.movify.data.TmdbMovieResponse
import retrofit2.Response
import retrofit2.http.GET

//A retrofit Network Interface for the Api
interface TmdbApi{
    @GET("movie/popular")
    fun getPopularMovie(): Deferred<Response<TmdbMovieResponse>>

}