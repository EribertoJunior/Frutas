package com.example.frutas.model.retrofit

import com.example.frutas.model.retrofit.service.DetalhesService
import com.example.frutas.model.retrofit.service.FrutaService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig(baseUrl: String, detailsUrl: String) {
    var interceptador : HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY)
    var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptador).build()

    private val retrofitFruts = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitDetails = Retrofit.Builder()
        .baseUrl(detailsUrl)
        .client(client)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun frutasService(): FrutaService = retrofitFruts.create(FrutaService::class.java)
    fun detalheDaFrutaService(): DetalhesService = retrofitDetails.create(DetalhesService::class.java)
}