package com.example.nhom6_datn.Retrofit

import android.app.Activity
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.AccessController

class ServiceGenator() {

    val _api = "http://do-an-aws.ap-southeast-2.elasticbeanstalk.com/"
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(_api).addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> builService (sevice: Class<T>): T {
        return retrofit.create(sevice)
    }
}