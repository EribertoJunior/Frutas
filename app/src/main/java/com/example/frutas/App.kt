package com.example.frutas

import android.app.Application
import com.example.frutas.di.BASE_URL
import com.example.frutas.di.DETAILS_URL
import com.example.frutas.di.setUpDI

open class App: Application() {
    override fun onCreate() {
        super.onCreate()
        BASE_URL = getBaseUrl()
        DETAILS_URL = getDetailsUrl()
        setUpDI()
    }

    open fun getBaseUrl() = BuildConfig.BASE_URL
    open fun getDetailsUrl() = BuildConfig.DETAILS_URL
}