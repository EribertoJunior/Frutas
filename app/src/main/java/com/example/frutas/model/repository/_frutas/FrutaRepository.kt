package com.example.frutas.model.repository._frutas

import com.example.frutas.model.entidades.Fruta
import com.example.frutas.model.retrofit.response.CallbackResponse

interface FrutaRepository {

    fun getListFruts(
        callbackResponse: CallbackResponse<ArrayList<Fruta>>
    )

    fun serachFrut(
        search:String,
        callbackResponse: CallbackResponse<ArrayList<Fruta>>
    )

}