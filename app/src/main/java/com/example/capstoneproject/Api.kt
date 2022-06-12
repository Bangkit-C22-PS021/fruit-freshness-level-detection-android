package com.example.capstoneproject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

interface Api {
    @GET("?api_key=abcd")
    fun getFruit() : Call<FruitData>

}