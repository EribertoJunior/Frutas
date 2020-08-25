package com.example.frutas

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.frutas.view.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val server: MockWebServer = MockWebServer()

    init {
        server.start(8081)
    }

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun quando_activity_abrir_edit_text_deve_ser_vazio() {
        onView(
            ViewMatchers.withId(R.id.etPesquisa)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("")
            )
        )
    }

    @Test
    fun quando_activity_abrir_swipe_deve_estar_visivel(){
        onView(ViewMatchers.withId(R.id.swipeRefresh_frutas))
            .check(ViewAssertions.matches(
            ViewMatchers.isDisplayed()
        ))
    }

    @Test
    fun quando_activite_abrir_botao_limpar_pesquisa_deve_estar_visivel(){
        onView(ViewMatchers.withId(R.id.btLimparPesquisa))
            .check(ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            ))
    }

    private fun loadContent(asset: String): String {
        val assets = InstrumentationRegistry.getInstrumentation().context.assets
        return assets.open(asset).reader().readText()
    }
}