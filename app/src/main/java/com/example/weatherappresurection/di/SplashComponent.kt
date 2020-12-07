package com.example.weatherappresurection.di

import com.example.core.di.BaseComponent
import com.example.weatherappresurection.ui.SplashActivity
import dagger.Component

@SplashScope
@Component(dependencies = [BaseComponent::class])
interface SplashComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: BaseComponent): SplashComponent
    }

    fun inject(activity: SplashActivity)
}