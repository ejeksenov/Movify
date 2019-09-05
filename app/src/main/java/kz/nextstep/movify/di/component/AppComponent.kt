package kz.nextstep.movify.di.component

import dagger.Component
import dagger.Module
import kz.nextstep.movify.di.module.CoroutineScopeModule
import kz.nextstep.movify.di.module.NetworkModule
import kz.nextstep.movify.ui.movieDetailedInfo.MovieDetailedViewModel
import kz.nextstep.movify.ui.movies.MovieListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (CoroutineScopeModule::class)])
interface AppComponent {

    fun inject(movieListViewModel: MovieListViewModel)
    fun inject(movieDetailedViewModel: MovieDetailedViewModel)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun networkModule(networkModule: NetworkModule): Builder
        fun coroutineScopModule(coroutineScopeModule: CoroutineScopeModule): Builder
    }
}