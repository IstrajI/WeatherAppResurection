package com.example.citylist.ui

import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.citylist.R
import com.example.citylist.di.DaggerCityListComponent
import com.example.citylist.ui.adapters.CityListAdapter
import com.example.citylist.ui.adapters.CitySuggestionsAdapter
import com.example.citylist.vm.CityListViewModel
import com.example.citylist.vm.CityListViewModelFactory
import com.example.core.di.BaseComponentWrapper
import com.example.core.model.CityWeather
import com.example.core.repository.WeatherRepository
import com.example.core.utils.notifyObserver
import com.example.weather_details.ui.WeatherDetailsActivity
import kotlinx.android.synthetic.main.city_fragment.*
import javax.inject.Inject

class CityListActivity : AppCompatActivity() {
    @Inject
    lateinit var service: WeatherRepository

    private lateinit var viewModel: CityListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCityListComponent.factory().create(BaseComponentWrapper.getBaseComponent())
            .inject(this)

        viewModel = ViewModelProvider(
            this,
            CityListViewModelFactory(service)
        ).get(CityListViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_fragment)

        initCityInput()
        initCityList()
    }

    private fun initCityInput() {
        cityInput.addTextChangedListener {
            val city = it.toString()

            if (city.length > 2) {
                viewModel.findCity(city)
            }
        }


        val adapter = CitySuggestionsAdapter(this, R.layout.suggestion_view)
        cityInput.setAdapter(adapter)
        cityInput.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            viewModel.cityList.value!!.add(adapter.getItem(position))
            viewModel.cityList.notifyObserver()
            cityInput.setText("")
        }

        viewModel.cityFindMatches.observe(this) {
            adapter.updateData(it)
        }
    }

    private fun initCityList() {
        val cityItemClickListener = object : CityListAdapter.CityItemClickListener {
            override fun onItemClick(city: CityWeather) {
                WeatherDetailsActivity.openWeatherDetails(this@CityListActivity, city)
            }

            override fun onRemoveClick(city: CityWeather) {
                viewModel.cityList.value!!.remove(city)
                viewModel.cityList.notifyObserver()
            }
        }

        val cityListAdapter = CityListAdapter(cityItemClickListener)
        cityList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        cityList.adapter = cityListAdapter

        viewModel.cityList.observe(this) {
            if (it.isNullOrEmpty()) return@observe
            cityListAdapter.update(it)
        }
    }
}