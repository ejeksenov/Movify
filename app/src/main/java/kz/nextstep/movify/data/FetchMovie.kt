package kz.nextstep.movify.data

data class TmdbMovie(
    val id: Int,
    val vote_average: Double,
    val title: String,
    val overview: String,
    val adult: Boolean,
    val poster_path: String,
    val release_date: String
)


// Data Model for the Response returned from the TMDB Api
data class TmdbMovieResponse(
    val results: List<TmdbMovie>
)



