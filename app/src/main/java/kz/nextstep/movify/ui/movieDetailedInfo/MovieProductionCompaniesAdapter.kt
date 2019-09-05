package kz.nextstep.movify.ui.movieDetailedInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kz.nextstep.movify.R
import kz.nextstep.movify.data.ProductionCompaniesData
import kz.nextstep.movify.data.TmdbMovie
import kz.nextstep.movify.utils.AppConstants
import kz.nextstep.movify.databinding.LayoutRawProductionCompaniesListBinding

class MovieProductionCompaniesAdapter: RecyclerView.Adapter<MovieProductionCompaniesAdapter.MovieProductionCompaniesViewHolder>()  {

    private lateinit var productionCompaniesList: MutableList<ProductionCompaniesData>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieProductionCompaniesViewHolder {
        val binding: LayoutRawProductionCompaniesListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_raw_production_companies_list, parent, false)
        return MovieProductionCompaniesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::productionCompaniesList.isInitialized) productionCompaniesList.size else 0
    }

    override fun onBindViewHolder(holder: MovieProductionCompaniesViewHolder, position: Int) {
        holder.bind(productionCompaniesList[position])
    }

    fun updateMovieProductionCompaniesList(productionCompaniesList: MutableList<ProductionCompaniesData>){
        this.productionCompaniesList = productionCompaniesList
        notifyDataSetChanged()
    }

    inner class MovieProductionCompaniesViewHolder(private val binding: LayoutRawProductionCompaniesListBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ProductionCompanyViewModel()

        fun bind(productionCompaniesData: ProductionCompaniesData) {
            viewModel.bind(productionCompaniesData)
            binding.productionCompaniesViewModel = viewModel
        }

    }
}
