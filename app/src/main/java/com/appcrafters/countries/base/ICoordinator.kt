package com.appcrafters.countries.base

import com.appcrafters.countries.base.model.Country

interface ICoordinator {
    fun showDetailsFragment(country: Country)
}