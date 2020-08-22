package com.example.frutas.model.retrofit.service

import com.example.frutas.model.entidades.Fruta
import com.example.frutas.model.retrofit.ResponseAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FrutaService {
    @GET
    fun carregarFrutas(
        @Query("search") nomeDaFruta: String = "all"
    ): Call<ResponseAPI<ArrayList<Fruta>>>
}