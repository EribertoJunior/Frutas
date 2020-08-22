package com.example.frutas.model.repository.modelresponseCustom

import com.example.frutas.model.entidades.Fruta
import com.example.frutas.model.repository.ResponseCustom
import com.example.frutas.model.repository.STATUS

class FrutaResponseCustom (
    var itens: ArrayList<Fruta> = arrayListOf(),
    errorMessage: String = "",
    status: STATUS
): ResponseCustom(errorMessage, status)