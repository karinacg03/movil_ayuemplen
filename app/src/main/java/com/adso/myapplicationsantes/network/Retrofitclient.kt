package com.adso.myapplicationsantes.network

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Response

// Definición del AuthInterceptor
class AuthInterceptor(private val sharedPreferences: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString("token", null)
        val requestBuilder = chain.request().newBuilder()
        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(requestBuilder.build())
    }
}


object Retrofitclient {
    private val BASE_URL = "https://proyectodani-production.up.railway.app/api/"

    private val loggingInterceptor=HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BODY
    }

    // Función para obtener el cliente de Retrofit configurado
    fun getClient(context: Context): Retrofit {
        val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        //  val token = sharedPreferences.getString("token", null)

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(AuthInterceptor(sharedPreferences))
        .build()

        // Crear instancia de Gson
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    lateinit var apiService: Apiservice

    fun initialize(context: Context) {
        apiService = getClient(context).create(Apiservice::class.java)
    }
}
