package kz.nextstep.movify.base

import androidx.lifecycle.ViewModel
import kz.nextstep.movify.di.component.AppComponent
import kz.nextstep.movify.di.component.DaggerAppComponent
import kz.nextstep.movify.di.module.CoroutineScopeModule
import kz.nextstep.movify.di.module.NetworkModule
import kz.nextstep.movify.ui.movieDetailedInfo.MovieDetailedViewModel
import kz.nextstep.movify.ui.movies.MovieListViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: AppComponent = DaggerAppComponent
        .builder()
        /*.networkModule(NetworkModule)
        .coroutineScopModule(CoroutineScopeModule)*/
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MovieListViewModel -> injector.inject(this)
            is MovieDetailedViewModel -> injector.inject(this)
        }


    }
}