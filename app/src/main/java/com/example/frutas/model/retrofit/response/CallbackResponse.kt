package com.example.frutas.model.retrofit.response

interface CallbackResponse<T> {
    fun success(response: T)

    fun failure(mensagem: String)
}