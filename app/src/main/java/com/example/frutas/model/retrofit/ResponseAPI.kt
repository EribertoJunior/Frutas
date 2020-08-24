package com.example.frutas.model.retrofit

class ResponseAPI<T>(
        var results: T,
        var tfvcount: Int,
        var error: String?
)