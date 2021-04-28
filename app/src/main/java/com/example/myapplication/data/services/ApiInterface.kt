package com.example.myapplication.data.services

import com.example.myapplication.data.model.PressReleaseDoc
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("getPressReleasesDocs")
    fun getPressRelease(): Call<PressReleaseDoc>
}