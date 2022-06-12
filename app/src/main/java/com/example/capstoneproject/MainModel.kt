package com.example.capstoneproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainModel : ViewModel()  {
    val listUser = MutableLiveData<ArrayList<FruitDataItem>>()

    fun setSeacrhUsers(){
        RetrofitClient
            .apiInstance
            .getFruit()
            .enqueue(object : Callback<FruitData> {
                override fun onResponse(call: Call<FruitData>, response: Response<FruitData>) {
                    listUser.postValue(response.body())
                }

                override fun onFailure(call: Call<FruitData>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }
            })
    }

    fun getSearchUser(): LiveData<ArrayList<FruitDataItem>> {
        return listUser
    }
}