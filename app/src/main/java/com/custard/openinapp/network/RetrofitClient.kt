package com.custard.openinapp.network

import com.custard.openinapp.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor())
                .build()
            ).build()
    }

    private fun loggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun apiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder().apply {
                addHeader("Authorization", "bearer ")
                build()
            }
            chain.proceed(request.build())
        }
    }

    fun getOpenInAppAPI(): OpenInAppApi {
        return getRetrofitClient()
            .create(OpenInAppApi::class.java)
    }
}