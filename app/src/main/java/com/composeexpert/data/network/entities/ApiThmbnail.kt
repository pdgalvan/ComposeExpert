package com.composeexpert.data.network.entities

data class ApiThumbnail(
    val extension: String,
    val path: String
)

fun ApiThumbnail.asString() = "$path.$extension".replace("http", "https")