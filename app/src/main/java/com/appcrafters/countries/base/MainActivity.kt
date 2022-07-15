package com.appcrafters.countries.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appcrafters.countries.R
import com.appcrafters.countries.base.model.Country
import com.appcrafters.countries.countrieslist.view.CountryListFragment
import com.appcrafters.countries.countriesdetails.view.CountryDetailsFragment

class MainActivity : AppCompatActivity(), ICoordinator {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        showFragment(CountryListFragment(), true)
    }

    private fun showFragment(fragment: Fragment, addAsRoot: Boolean = false) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        if (!addAsRoot) transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showDetailsFragment(country: Country) {
        showFragment(CountryDetailsFragment.newInstance(country))
    }

}