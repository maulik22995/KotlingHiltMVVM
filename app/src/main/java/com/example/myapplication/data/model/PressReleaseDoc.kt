package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PressReleaseDoc(
    val content: ArrayList<Content>,
    val lang: String,
    val status: Boolean
)