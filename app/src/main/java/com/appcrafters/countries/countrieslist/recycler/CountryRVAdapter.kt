package com.appcrafters.countries.countrieslist.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.appcrafters.countries.R
import com.appcrafters.countries.base.model.Country

class CountryRVAdapter(private val countries: List<Country>, private val onItemClicked: (Country) -> Unit) :
    Adapter<CountryRVViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryRVViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
    )

    override fun onBindViewHolder(holder: CountryRVViewHolder, position: Int) {
        holder.bind(countries[position], onItemClicked)
    }

    override fun getItemCount() = countries.size
}