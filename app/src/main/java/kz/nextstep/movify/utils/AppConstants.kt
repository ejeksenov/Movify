package kz.nextstep.movify.utils

import kz.nextstep.movify.BuildConfig

object AppConstants {
    var tmdbApiKey = BuildConfig.TMDB_API_KEY
    var TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    var TMDB_POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"
}