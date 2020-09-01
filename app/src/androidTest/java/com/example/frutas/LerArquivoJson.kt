package com.example.frutas

import androidx.test.platform.app.InstrumentationRegistry

object LerArquivoJson {
    fun lerStringDoArquivo(fileName: String): String {
        val assets = InstrumentationRegistry.getInstrumentation().context.assets
        return assets.open(fileName).reader().readText()
    }
}