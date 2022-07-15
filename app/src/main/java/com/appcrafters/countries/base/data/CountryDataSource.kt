package com.appcrafters.countries.base.data

import com.appcrafters.countries.base.functional.Either
import com.appcrafters.countries.base.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

interface ICountryDataSource {
    suspend fun getAllCountries(): Either<List<Country>>
}

class CountryDataSource(private val apiService: CountryApiService) : ICountryDataSource {
    override suspend fun getAllCountries(): Either<List<Country>> = handleCall(apiService.getAllCountries())

    private suspend fun <T> handleCall(call: Call<T>): Either<T> {
        return withContext(Dispatchers.IO) {
            val response = call.execute()

            if (response.isSuccessful) {
                Either.Success(response.body()!!)
            } else {
                Either.Error(Exception(response.message()))
            }
        }
    }
}