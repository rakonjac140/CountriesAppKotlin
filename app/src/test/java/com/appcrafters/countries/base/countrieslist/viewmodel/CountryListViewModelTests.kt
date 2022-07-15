package com.appcrafters.countries.base.countrieslist.viewmodel

import androidx.lifecycle.Observer
import com.appcrafters.countries.base.InstantExecutorTest
import com.appcrafters.countries.base.data.ICountryDataSource
import com.appcrafters.countries.base.functional.Either
import com.appcrafters.countries.base.model.Country
import com.appcrafters.countries.countrieslist.view.CountryListViewState
import com.appcrafters.countries.countrieslist.view.CountryListViewState.*
import com.appcrafters.countries.countrieslist.viewmodel.CountryListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.openMocks

class CountryListViewModelTests : InstantExecutorTest() {
    @Mock
    lateinit var dataSource: ICountryDataSource

    @Mock
    lateinit var stateObserver: Observer<CountryListViewState>

    lateinit var viewModel: CountryListViewModel

    @Before
    fun setUp() {
        openMocks(this)
        viewModel = CountryListViewModel(dataSource)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `testGetCountries, has result, state changed to Proccessing - DataReceived`() = runBlocking {
        val expectedResult: List<Country> = listOf()

        `when`(dataSource.getAllCountries()).thenReturn(Either.Success(expectedResult))

        viewModel.getCountries()

        verify(stateObserver).onChanged(Proccessing)
        verify(stateObserver).onChanged(DataReceived(expectedResult))
    }

    @Test
    fun `testGetCountries, has error, state changed to Proccessing - ErrorReceived`() = runBlocking {
        val expectedError = Exception("test")

        `when`(dataSource.getAllCountries()).thenReturn(Either.Error(expectedError))

        viewModel.getCountries()

        verify(stateObserver).onChanged(ErrorReceived(expectedError.toString()))
    }
}