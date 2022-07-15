package com.appcrafters.countries.countrieslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcrafters.countries.base.data.ICountryDataSource
import com.appcrafters.countries.base.functional.Either
import com.appcrafters.countries.countrieslist.view.CountryListViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryListViewModel(private val dataSource: ICountryDataSource) : ViewModel() {

    private val _state = MutableLiveData<CountryListViewState>()
    val state: LiveData<CountryListViewState>
        get() = _state

    fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {

            _state.postValue(CountryListViewState.Proccessing)

            _state.postValue(
                when (val result = dataSource.getAllCountries()) {
                    is Either.Success -> CountryListViewState.DataReceived(result.data)
                    is Either.Error -> CountryListViewState.ErrorReceived(result.exception.toString())
                }
            )
        }
    }
}