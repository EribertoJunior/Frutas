package com.example.frutas

import androidx.test.platform.app.InstrumentationRegistry
import java.io.IOException
import java.io.InputStreamReader

object LerArquivoJson {
    fun lerStringDoArquivo(fileName: String): String {
        /*val assets = InstrumentationRegistry.getInstrumentation().context.assets
        return assets.open(fileName).reader().readText()*/
        try {
            val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApp).assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        }catch (e: IOException){
            throw e
        }
    }
}