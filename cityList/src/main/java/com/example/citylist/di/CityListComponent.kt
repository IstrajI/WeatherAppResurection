package com.example.citylist.di

import com.example.citylist.ui.CityListActivity
import com.example.core.di.BaseComponent
import dagger.Component

@CityListScope
@Component(dependencies = [BaseComponent::class])
interface CityListComponent {
    @Component.Factory
    interface Factory {
        fun create(appComponent: BaseComponent): CityListComponent
    }

    fun inject(activity: CityListActivity)
}