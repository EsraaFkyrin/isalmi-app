package com.example.isalmiapp.api

import retrofit2.Call
import retrofit2.http.GET

interface WebService {
//https://mp3quran.net/api/v3/radios

    @GET("radios")
    fun getRadio(): Call<RadioResponse>


}