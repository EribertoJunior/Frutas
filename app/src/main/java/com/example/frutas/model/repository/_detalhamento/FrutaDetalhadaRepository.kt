package com.example.frutas.model.repository._detalhamento

import com.example.frutas.model.entidades.FrutaDetalhada
import com.example.frutas.model.retrofit.response.CallbackResponse

interface FrutaDetalhadaRepository {
    fun getDetailFruts(
        nomeDaFruta:String,
        callbackResponse: CallbackResponse<ArrayList<FrutaDetalhada>>
    )
}