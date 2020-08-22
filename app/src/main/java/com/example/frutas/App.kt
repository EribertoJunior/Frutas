package com.example.frutas

import android.app.Application
import com.example.frutas.di.setUpDI

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        setUpDI()
    }
}