package com.example.frutas.di

import android.app.Application
import com.example.frutas.di.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

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
    }
}