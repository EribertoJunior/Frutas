package com.example.frutas.model.retrofit.service

import com.example.frutas.model.entidades.Fruta
import com.example.frutas.model.entidades.FrutaDetalhada
import com.example.frutas.model.retrofit.ResponseAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DetalhesService {
    @GET("tfvjsonapi.php")
    fun carregarFrutas(
        @Query("tfvitem") nomeDaFruta: String
    ): Call<ResponseAPI<ArrayList<FrutaDetalhada>>>
}