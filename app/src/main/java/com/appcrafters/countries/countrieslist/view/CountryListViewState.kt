package com.appcrafters.countries.countrieslist.view

import com.appcrafters.countries.base.model.Country

sealed class CountryListViewState {

    object Proccessing : CountryListViewState()
    data class DataReceived(val countries: List<Country>) : CountryListViewState()
    data class ErrorReceived(val message: String) : CountryListViewState()
}