package com.example.frutas.model.entidades

class FrutaDetalhada(
    tfvname: String,
    botname: String,
    othname: String,
    imageurl: String,
    var description: String,
    var uses: String,
    var propagation: String,
    var soil: String,
    var climate: String,
    var health: String
) : Fruta(
    tfvname, botname, othname, imageurl
)