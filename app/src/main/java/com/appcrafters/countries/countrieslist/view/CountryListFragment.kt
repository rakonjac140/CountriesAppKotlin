package com.appcrafters.countries.countrieslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.appcrafters.countries.R
import com.appcrafters.countries.base.ICoordinator
import com.appcrafters.countries.base.data.ApiServiceProvider
import com.appcrafters.countries.base.data.CountryDataSource
import com.appcrafters.countries.base.functional.ViewModelFactoryUtil
import com.appcrafters.countries.base.model.Country
import com.appcrafters.countries.countrieslist.viewmodel.CountryListViewModel
import com.appcrafters.countries.countrieslist.recycler.CountryRVAdapter
import kotlinx.android.synthetic.main.fragment_country_list.*

class CountryListFragment : Fragment() {

    lateinit var viewModel: CountryListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelFactoryUtil.viewModelFactory {
            CountryListViewModel(CountryDataSource(ApiServiceProvider.countriesApiService))
        }).get(CountryListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindFromViewModel()
        viewModel.getCountries()
    }

    private fun setUpRecyclerView(countries: List<Country>) {
        countryListRV.adapter = CountryRVAdapter(countries) { country ->
            (activity as ICoordinator).showDetailsFragment(country)
        }
    }

    private fun bindFromViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->

            countryListProgressBar.isVisible = state is CountryListViewState.Proccessing

            when (state) {
                is CountryListViewState.DataReceived -> { setUpRecyclerView(state.countries) }
                is CountryListViewState.ErrorReceived -> showError(state.message)
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}