package com.example.weather_details.di

import com.example.core.di.BaseComponent
import com.example.weather_details.ui.WeatherDetailsActivity
import dagger.Component

@WeatherDetailsScope
@Component(dependencies = [BaseComponent::class])
interface WeatherDetailsComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: BaseComponent): WeatherDetailsComponent
    }

    fun inject(activity: WeatherDetailsActivity)
}