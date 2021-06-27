package com.amroid.dawa.api

import com.amroid.dawa.model.UserAnalysis
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @GET("/PreCustomer/userAnalysis")
    fun  getUserAnalysis():Call<UserAnalysis>



    companion object{
        val END_POINT="http://roshta.pharmaclue.com"
    }
}