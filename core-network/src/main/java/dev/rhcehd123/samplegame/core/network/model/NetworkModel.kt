package dev.rhcehd123.samplegame.core.network.model

data class RequestGachaBody(
    val apiKey: String,
    val gachaId: Int,
    val userId: String,
    val count: Int,
)