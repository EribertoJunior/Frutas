package com.example.frutas.model.repository.modelresponseCustom

import com.example.frutas.model.entidades.Fruta
import com.example.frutas.model.entidades.FrutaDetalhada
import com.example.frutas.model.repository.ResponseCustom
import com.example.frutas.model.repository.STATUS

class FrutaDetalhadaResponseCustom (
    var frutaDetalhada: FrutaDetalhada? = null,
    errorMessage: String = "",
    status: STATUS
): ResponseCustom(errorMessage, status)