package com.appcrafters.countries.base.data

import com.appcrafters.countries.base.functional.Either
import com.appcrafters.countries.base.model.Country
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import retrofit2.Call
import retrofit2.Response

class CountryDataSourceTests {

    @Mock
    lateinit var apiService: CountryApiService

    @Mock
    lateinit var getCountriesCall: Call<List<Country>>

    lateinit var dataSource: CountryDataSource

    @Before
    fun setUp() {
        openMocks(this)
        dataSource = CountryDataSource(apiService)
    }

    @Test
    fun `testGetCountries, has response, Success returned`() = runBlocking {
        val expectedCountries: List<Country> = listOf()
        val expectedResult = Either.Success(expectedCountries)

        `when`(apiService.getAllCountries()).thenReturn(getCountriesCall)
        `when`(getCountriesCall.execute()).thenReturn(Response.success(expectedCountries))

        val result = dataSource.getAllCountries()

        assertEquals(expectedResult, result)
    }

    @Test
    fun `testGetCountries, has error, Error returned`() = runBlocking {
        val expectedResponseBody = ResponseBody.create(
            MediaType.parse("application/json"), ""
        )

        `when`(apiService.getAllCountries()).thenReturn(getCountriesCall)
        `when`(getCountriesCall.execute()).thenReturn(Response.error(400, expectedResponseBody))


        val result = dataSource.getAllCountries()

        assertTrue(result is Either.Error)
    }
}