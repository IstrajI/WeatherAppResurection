package com.example.core.di

class BaseComponentWrapper private constructor() {
    private var baseComponent: BaseComponent? = null

    fun initializeComponent(): BaseComponent? {
        baseComponent = DaggerBaseComponent.create()
        return baseComponent
    }

    companion object {
        private var baseComponentWrapper: BaseComponentWrapper? = null

        private fun getInstance(): BaseComponentWrapper? {
            if (baseComponentWrapper == null) {
                baseComponentWrapper = BaseComponentWrapper()
                baseComponentWrapper!!.initializeComponent()
            }
            return baseComponentWrapper
        }

        fun getBaseComponent() = getInstance()!!.baseComponent!!
    }
}
