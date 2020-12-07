package com.example.citylist.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.repository.WeatherRepository

class CityListViewModelFactory(val repositoryImpl: WeatherRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CityListViewModel(repositoryImpl) as T
    }
}