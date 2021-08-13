package com.example.composeapp.response

data class Track(
    val artists: List<Artist>,
    val hub: Hub,
    val images: ImagesX,
    val key: String,
    val layout: String,
    val share: Share,
    val subtitle: String,
    val title: String,
    val type: String,
    val url: String
)