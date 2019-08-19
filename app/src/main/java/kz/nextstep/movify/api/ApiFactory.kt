package kz.nextstep.movify.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kz.nextstep.movify.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//ApiFactory to create TMDB Api
object ApiFactory {

    //Creating Auth Interceptor to add api_key query in front of all the requests.
    private var authInterceptor = Interceptor {chain ->
        val  newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", AppConstants.tmdbApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    //OkhttpClient for building http request url
    private val tmdbClient = OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(tmdbClient)
        .baseUrl(AppConstants.TMDB_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val tmdbApi: TmdbApi = retrofit()
        .create(TmdbApi::class.java)
}