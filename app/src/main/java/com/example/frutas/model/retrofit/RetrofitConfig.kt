package com.example.frutas.model.retrofit

import com.example.frutas.model.retrofit.service.DetalhesService
import com.example.frutas.model.retrofit.service.FrutaService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig(baseUrl: String, detailsUrl: String) {
    private val retrofitFruts = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpProvider.getOkHttpProvider())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitDetails = Retrofit.Builder()
        .baseUrl(detailsUrl)
        .client(OkHttpProvider.getOkHttpProvider())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun frutasService(): FrutaService = retrofitFruts.create(FrutaService::class.java)
    fun detalheDaFrutaService(): DetalhesService = retrofitDetails.create(DetalhesService::class.java)
}