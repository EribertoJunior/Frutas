package com.example.frutas

import com.example.frutas.di.BASE_URL
import com.example.frutas.di.DETAILS_URL
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockWebServerRule : TestWatcher() {
    private val LOCAL_HOST :String = "http://localhost:8080/"

    val mock : MockWebServer by lazy {
        MockWebServer()
    }

    override fun starting(description: Description?) {
        super.starting(description)
        mock.start()
        BASE_URL = mock.url(LOCAL_HOST).toString()
        DETAILS_URL = mock.url(LOCAL_HOST).toString()
    }

    override fun finished(description: Description?) {
        super.finished(description)
        mock.shutdown()
    }
}