package com.example.frutas.di

import android.app.Application
import com.example.frutas.BuildConfig
import com.example.frutas.di.module.PROPERTY_BASE_URL
import com.example.frutas.di.module.PROPERTY_DETAILS_URL
import com.example.frutas.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

var BASE_URL: String = BuildConfig.BASE_URL
var DETAILS_URL: String = BuildConfig.DETAILS_URL

fun Application.setUpDI() {

    startKoin {
        androidLogger()
        androidContext(this@setUpDI)

        properties(
            mapOf(

            )
        )

        modules(
            listOf(appModule)
        )

        properties(mapOf(PROPERTY_BASE_URL to BASE_URL, PROPERTY_DETAILS_URL to DETAILS_URL))
    }
}