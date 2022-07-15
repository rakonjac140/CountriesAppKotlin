package com.appcrafters.countries.base.data

import com.appcrafters.countries.base.data.CountryApiService

object ApiServiceProvider {
    val countriesApiService = RetrofitBuilder.retrofit.create(CountryApiService::class.java)
}