package com.appcrafters.countries.countrieslist.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.appcrafters.countries.base.model.Country
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_country.view.*;

class CountryRVViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(country: Country, onItemClicked: (Country) -> Unit) {
        Glide.with(itemView).load(country.flags.png).into(itemView.countryCoverImg)

        itemView.countryNameTxt.text = country.name

        itemView.setOnClickListener {
            onItemClicked.invoke(country)
        }
    }
}