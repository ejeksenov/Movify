package kz.nextstep.movify.api

import kotlinx.coroutines.Deferred
import kz.nextstep.movify.data.TmdbMovie
import kz.nextstep.movify.data.TmdbMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//A retrofit Network Interface for the Api
interface TmdbApi{
    @GET("movie/popular")
    fun getPopularMovie(): Deferred<Response<TmdbMovieResponse>>

    @GET("movie/top_rated")
    fun getTopRatedMovie(): Deferred<Response<TmdbMovieResponse>>

    @GET("movie/{id}")
    fun getMovie(@Path("id") id: Int): Deferred<Response<TmdbMovie>>
}