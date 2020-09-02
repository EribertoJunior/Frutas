package com.example.frutas

import com.example.frutas.di.BASE_URL
import com.example.frutas.di.DETAILS_URL

class TestApp : App() {
    var url = "http://127.0.0.1:8080/"

    override fun onCreate() {
        super.onCreate()
        BASE_URL = getBaseUrl()
        DETAILS_URL = getDetailsUrl()
    }

    override fun getBaseUrl(): String {
        return url
    }

    override fun getDetailsUrl(): String {
        return url
    }

}