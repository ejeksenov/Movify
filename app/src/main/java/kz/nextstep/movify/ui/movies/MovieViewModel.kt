package kz.nextstep.movify.ui.movies

import androidx.lifecycle.MutableLiveData
import kz.nextstep.movify.base.BaseViewModel
import kz.nextstep.movify.data.*
import kz.nextstep.movify.utils.ChangeDateFormat

class MovieViewModel: BaseViewModel() {

    private val id = MutableLiveData<Int>()
    private val title = MutableLiveData<String>()
    private val voteAverage = MutableLiveData<String>()
    private val overview = MutableLiveData<String>()
    private val adult = MutableLiveData<Boolean>()
    private val poster_path = MutableLiveData<String>()
    private val release_date = MutableLiveData<String>()
    private val genres = MutableLiveData<String>()
    private val production_companies = MutableLiveData<MutableList<ProductionCompaniesData>>()
    private val spoken_languages = MutableLiveData<String>()
    private val runtime = MutableLiveData<String>()


    fun bind(tmdbMovie: TmdbMovie) {
        id.value = tmdbMovie.id
        title.value = tmdbMovie.title
        voteAverage.value = tmdbMovie.vote_average.toString()
        overview.value = tmdbMovie.overview
        adult.value = tmdbMovie.adult
        poster_path.value = tmdbMovie.poster_path
        release_date.value = onGetReleaseDateCountry(tmdbMovie.release_date, tmdbMovie.production_countries)
        genres.value = onSumGenres(tmdbMovie.genres)
        production_companies.value = tmdbMovie.production_companies
        spoken_languages.value = onGetSpokenLanguages(tmdbMovie.spoken_languages)
        runtime.value = tmdbMovie.runtime.toString()
    }

    private fun onGetSpokenLanguages(spoken_languages: MutableList<SpokenLanguagesData>?): String? {
        var spokenLanguages = "<b>Languages</b><br>"
        spoken_languages?.forEachIndexed { index, spokenLanguagesData ->
            var additionalComa = ", "
            if (index == spoken_languages.size.minus(1))
                additionalComa = ""
            spokenLanguages += spokenLanguagesData.name + additionalComa
        }
        return spokenLanguages
    }

    private fun onGetReleaseDateCountry(
        release_date: String?,
        production_countries: MutableList<ProductionCountriesData>?
    ): String? {
        val releaseDate = ChangeDateFormat.onChangeDateFormat(release_date)
        var countries = ""
        production_countries?.forEachIndexed { index, productionCountriesData ->
            var additionalComa = ", "
            if (index == production_countries.size.minus(1))
                additionalComa = ""
            countries += productionCountriesData.name + additionalComa
        }
        return "$releaseDate, $countries"
    }

    private fun onSumGenres(genres: MutableList<GenresData>?): String? {
        var genresStr = "<b>Genre</b><br>"
        genres?.forEachIndexed { index, genresData ->
            var additionalComa = ", "
            if (index == genres.size.minus(1))
                additionalComa = ""
            genresStr += genresData.name + additionalComa
        }

        return genresStr
    }


    fun getId(): MutableLiveData<Int> {
        return id
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getVoteAverage(): MutableLiveData<String> {
        return voteAverage
    }

    fun getOverview(): MutableLiveData<String> {
        return overview
    }

    fun getAdult(): MutableLiveData<Boolean> {
        return adult
    }

    fun getPosterPath(): MutableLiveData<String> {
        return poster_path
    }

    fun getReleaseDate(): MutableLiveData<String> {
        return release_date
    }

    fun getGenres(): MutableLiveData<String> {
        return genres
    }

    fun getProductionCompanies(): MutableLiveData<MutableList<ProductionCompaniesData>> {
        return production_companies
    }

    fun getSpokenLanguages(): MutableLiveData<String> {
        return spoken_languages
    }

    fun getRuntime(): MutableLiveData<String> {
        return runtime
    }


}