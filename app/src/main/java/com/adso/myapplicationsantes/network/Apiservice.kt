package com.adso.myapplicationsantes.network

import com.adso.myapplicationsantes.models.Loginrequest
import com.adso.myapplicationsantes.models.Loginresponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Apiservice {
    @POST("login")
    fun login(@Body request:Loginrequest):Call<Loginresponse>
    @POST ("logout")
    fun logout():Call<Void>
}
