package com.example.frutas

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.frutas.view.DetalhesDaFrutaActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetalhesDaFrutaActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(DetalhesDaFrutaActivity::class.java)
    }

    @Test
    fun quando_a_activity_abrir_imagem_devem_estar_visivel(){
        onView(ViewMatchers.withId(R.id.ivFruta)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun quando_a_activity_abrir_descricao_deve_estar_visivel(){
        onView(ViewMatchers.withId(R.id.tvDescricaoCompleta)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun quando_a_activity_abrir_nome_bots_devem_estar_visiveis(){
        onView(ViewMatchers.withId(R.id.tvNomesBots)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun quando_a_activity_abrir_outros_nomes_devem_estar_visiveis(){
        onView(ViewMatchers.withId(R.id.tvOutrosNomes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
    @Test
    fun quando_a_activity_abrir_progressBar_deve_estar_visivel(){
        onView(ViewMatchers.withId(R.id.progressBar2)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}