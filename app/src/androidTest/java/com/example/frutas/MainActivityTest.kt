package com.example.frutas

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.frutas.model.retrofit.OkHttpProvider
import com.example.frutas.view.MainActivity
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private val MOCK_FRUTS_RESPONSE_OK = "mock_fruts_response_ok.json"
    private val MOCK_FRUTS_RESPONSE_ERROR = "mock_fruts_response_error.json"

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private val mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                OkHttpProvider.getOkHttpProvider()
            )
        )
    }

    @After
    fun teardowm() {
        mockWebServer.shutdown()
    }

    @Test
    fun quando_activity_abrir_edit_text_deve_ser_vazio() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return mockResponse(MOCK_FRUTS_RESPONSE_OK)
            }
        }
        activityRule.launchActivity(null)
        //getResponseOk()
        onView(
            withId(R.id.etPesquisa)
        ).check(
            matches(
                withText("")
            )
        )
    }

    @Test
    fun testSuccessfulResponse() {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return mockResponse(MOCK_FRUTS_RESPONSE_OK)
            }
        }

        activityRule.launchActivity(null)

        onView(withId(R.id.rvListaDeFrutas))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

    }

    @Test
    fun quando_activity_abrir_swipe_deve_estar_visivel() {
        onView(withId(R.id.swipeRefresh_frutas))
            .check(
                matches(
                    isDisplayed()
                )
            )
    }

    @Test
    fun quando_activite_abrir_botao_limpar_pesquisa_deve_estar_visivel() {
        onView(withId(R.id.btLimparPesquisa))
            .check(
                matches(
                    isDisplayed()
                )
            )
    }

    @Test
    fun quanto_activite_abrir_pesquisar_por_nome_deve_ser_possivel() {
        onView(withId(R.id.etPesquisa)).perform(typeText("bana"))
        onView(withText("Banana")).check(matches(isDisplayed()))
    }

    private fun getResponseOk() {
        mockResponse(MOCK_FRUTS_RESPONSE_OK)
    }

    private fun getResponseError() {
        mockResponse(MOCK_FRUTS_RESPONSE_ERROR)
    }

    private fun mockResponse(asset: String, responseCode: Int = 200): MockResponse {
        //mockWebServer.enqueue(
        return MockResponse()
            .setResponseCode(responseCode)
            .setBody(LerArquivoJson.lerStringDoArquivo(asset))
        //)
    }

}