package kz.nextstep.movify.data

data class TmdbMovie(
    val id: Int? = null,
    val vote_average: Double? = null,
    val title: String? = null,
    val overview: String? = null,
    val adult: Boolean? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val genres: MutableList<GenresData>? = null,
    val production_countries: MutableList<ProductionCountriesData>? = null,
    val production_companies: MutableList<ProductionCompaniesData>? = null,
    val spoken_languages: MutableList<SpokenLanguagesData>? = null,
    val runtime: Int? = null
)


// Data Model for the Response returned from the TMDB Api
data class TmdbMovieResponse(
    val results: List<TmdbMovie>
)


data class GenresData(
    val id: Int,
    val name: String
)

data class ProductionCountriesData(
    val iso_3166_1: String,
    val name: String
)


data class ProductionCompaniesData(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class SpokenLanguagesData(
    val iso_639_1: String,
    val name: String
)

