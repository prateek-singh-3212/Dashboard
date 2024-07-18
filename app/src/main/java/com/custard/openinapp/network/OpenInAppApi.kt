package com.custard.openinapp.network

import com.custard.openinapp.network.data.DashboardData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenInAppApi {

    @GET("api/v1/dashboardNew")
    suspend fun fetchDashboardData(
        @Header("Authorization") token: String,
    ): Response<DashboardData>

//    Example of post api call
//    @POST("api/v1/dashboardNew")
//    suspend fun fetchDashboardData(
//       @Header("Authorization") token: String,
//       @Body data: TempData
//    ): Response<DashboardData>
}