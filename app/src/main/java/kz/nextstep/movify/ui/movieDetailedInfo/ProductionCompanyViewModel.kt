package kz.nextstep.movify.ui.movieDetailedInfo

import androidx.lifecycle.MutableLiveData
import kz.nextstep.movify.base.BaseViewModel
import kz.nextstep.movify.data.ProductionCompaniesData

class ProductionCompanyViewModel: BaseViewModel() {

    private val logo_path =  MutableLiveData<String>()
    private val name = MutableLiveData<String>()

    fun bind(productionCompaniesData: ProductionCompaniesData) {
        logo_path.value = productionCompaniesData.logo_path
        name.value = productionCompaniesData.name
    }

    fun getLogoPath(): MutableLiveData<String> {
        return logo_path
    }

    fun getName(): MutableLiveData<String> {
        return name
    }
}