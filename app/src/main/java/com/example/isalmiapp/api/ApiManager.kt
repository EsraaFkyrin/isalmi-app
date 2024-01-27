package com.example.isalmiapp.api

import android.util.Log
import com.example.isalmiapp.constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiManager {
//https://mp3quran.net/api/v3/radios
    companion object {

    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit {

        val httpLoggingInterceptor=HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
               Log.e("api",message)

            }
        })
        val okHttpClient=OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(constant.Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


    }
    return retrofit!!
}

    fun getWebServices() :WebService{
        return getInstance().create(WebService::class.java)
    }

}




}