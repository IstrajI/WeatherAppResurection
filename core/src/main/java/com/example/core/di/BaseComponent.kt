package com.example.core.di

import com.example.core.repository.WeatherRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkingModule::class])
interface BaseComponent {
    fun githubApiService(): WeatherRepository
}