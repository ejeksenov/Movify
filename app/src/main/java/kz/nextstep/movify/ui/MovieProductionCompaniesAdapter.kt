package kz.nextstep.movify.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kz.nextstep.movify.R
import kz.nextstep.movify.data.ProductionCompaniesData
import kz.nextstep.movify.utils.AppConstants

class MovieProductionCompaniesAdapter(context: Context,
                                      val productionCompaniesList: MutableList<ProductionCompaniesData>): RecyclerView.Adapter<MovieProductionCompaniesAdapter.MovieProductionCompaniesViewHolder>()  {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieProductionCompaniesViewHolder {
        val view: View = inflater.inflate(R.layout.layout_raw_production_companies_list, parent, false)
        return MovieProductionCompaniesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productionCompaniesList.size
    }

    override fun onBindViewHolder(holder: MovieProductionCompaniesViewHolder, position: Int) {
        val productionCompaniesData = productionCompaniesList[position]

        Picasso.get().load(AppConstants.TMDB_POSTER_BASE_URL + productionCompaniesData.logo_path).placeholder(R.drawable.production_company_logo_placeholder).into(holder.iv_raw_production_companies_logo)
        holder.tv_raw_production_companies_name.text = productionCompaniesData.name
    }

    inner class MovieProductionCompaniesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_raw_production_companies_logo = itemView.findViewById(R.id.iv_raw_production_companies_logo) as ImageView
        var tv_raw_production_companies_name = itemView.findViewById(R.id.tv_raw_production_companies_name) as TextView
    }
}