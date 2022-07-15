package com.appcrafters.countries.base.data

import com.appcrafters.countries.base.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface CountryApiService {

    @GET("all")
    fun getAllCountries(): Call<List<Country>>
}