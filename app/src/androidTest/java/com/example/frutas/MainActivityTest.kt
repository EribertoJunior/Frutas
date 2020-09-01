package com.example.frutas

import android.app.Activity
import android.app.Instrumentation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.frutas.model.retrofit.OkHttpProvider
import com.example.frutas.view.DetalhesDaFrutaActivity
import com.example.frutas.view.MainActivity
import com.example.frutas.view.adapters.AdapterFrutas
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
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
        mockResponse(MOCK_FRUTS_RESPONSE_OK)
        activityRule.launchActivity(null)
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
        mockResponse(MOCK_FRUTS_RESPONSE_OK)
        activityRule.launchActivity(null)

        onView(withId(R.id.swipeRefresh_frutas))
            .check(matches(isDisplayed()))

    }

    @Test
    fun quando_activity_abrir_swipe_deve_estar_visivel() {
        mockResponse(MOCK_FRUTS_RESPONSE_OK)
        activityRule.launchActivity(null)
        onView(withId(R.id.swipeRefresh_frutas))
            .check(
                matches(
                    isDisplayed()
                )
            )
    }

    @Test
    fun teste_de_navegacao(){
        mockResponse(MOCK_FRUTS_RESPONSE_OK)
        activityRule.launchActivity(null)
        Intents.init()
        val matcher = hasComponent(DetalhesDaFrutaActivity::class.java.name)

        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, null)
        intending(matcher).respondWith(result)

        //onView(withId(R.id.login_button)).perform(click())

        onView(withId(R.id.rvListaDeFrutas))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<AdapterFrutas.ViewHolder>(
                    0,
                    click()
                )
            )

        intended(matcher)
        Intents.release()
    }

    @Test
    fun quando_activite_abrir_botao_limpar_pesquisa_deve_estar_visivel() {
        mockResponse(MOCK_FRUTS_RESPONSE_OK)
        activityRule.launchActivity(null)
        onView(withId(R.id.btLimparPesquisa))
            .check(
                matches(
                    isDisplayed()
                )
            )
    }

    @Test
    fun quanto_activite_abrir_pesquisar_por_nome_deve_ser_possivel() {
        mockResponse(MOCK_FRUTS_RESPONSE_OK)
        activityRule.launchActivity(null)
        mockResponse(MOCK_FRUTS_RESPONSE_OK)
        onView(withId(R.id.etPesquisa)).perform(typeText("b"), closeSoftKeyboard())
        mockResponse(MOCK_FRUTS_RESPONSE_OK)
        onView(withId(R.id.etPesquisa)).perform(typeText("a"), closeSoftKeyboard())
        mockResponse(MOCK_FRUTS_RESPONSE_OK)
        Espresso.closeSoftKeyboard()
        onView(withText("Banana")).check(matches(isDisplayed()))
    }

    private fun mockResponse(asset: String, responseCode: Int = 200) {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(LerArquivoJson.lerStringDoArquivo(asset))
        )
    }

}