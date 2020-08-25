package com.example.frutas

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.frutas.view.MainActivity
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    //@get:Rule
    val mockWebServer = MockWebServerRule()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun quando_activity_abrir_edit_text_deve_ser_vazio() {
        onView(
            withId(R.id.etPesquisa)
        ).check(
            matches(
                withText("")
            )
        )
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

    private fun mockResponse(responseCode: Int = 200, asset: String) {
        mockWebServer.mock.enqueue(
            MockResponse().setResponseCode(responseCode).setBody(loadContent(asset))
        )
    }

    private fun loadContent(asset: String): String {
        val assets = InstrumentationRegistry.getInstrumentation().context.assets
        return assets.open(asset).reader().readText()
    }
}