package com.appcrafters.countries.countriesdetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.appcrafters.countries.R
import com.appcrafters.countries.base.model.Country
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_country_details.*

class CountryDetailsFragment : Fragment() {

    companion object {
        private var COUNTRY: Country = Country()

        fun newInstance(country: Country): CountryDetailsFragment {

            return CountryDetailsFragment().apply {
                arguments = Bundle().apply {
                    COUNTRY = country
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFromViewModel(COUNTRY)
    }

    private fun bindFromViewModel(country: Country) {
        setUpView(country)
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpView(country: Country) {
        Glide.with(this).load(country.flags.png).into(countryImg)

        nameTxt.text = country.name
        capitalTxt.text = "Capital: " + country.capital
        areaTxt.text = country.area.toString()
        populationTxt.text = country.population.toString()
        regionTxt.text = country.region
        subregionTxt.text = country.subregion

        var lang: String = ""

        country.languages.forEach {
            lang += "* " + it.name + "\n"
        }

        languagesTxt.text = lang
        if (country.independent)
            independentTxt.text = "This country is independent!"
        else
            independentTxt.text = "This country is not independent!"
        //PLACAJU SE GOOGLE MAPE, NE MOZE VISE MAPA
//        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?
//        mapFragment?.getMapAsync { map ->
//            val capitalLocation = LatLng(country.latlng[0], country.latlng[1])
//            map.addMarker(MarkerOptions().position(capitalLocation).title(country.name))
//            map.animateCamera(CameraUpdateFactory.newLatLngZoom(capitalLocation, 15F))
    }
}