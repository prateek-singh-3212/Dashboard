package com.custard.openinapp.repo

import com.custard.openinapp.network.OpenInAppApi
import com.custard.openinapp.network.data.DashboardData
import com.custard.openinapp.util.Constants
import com.custard.openinapp.util.OpenInAppSharedPreference
import com.custard.openinapp.util.Result

class Repository(
    private val openInAppApi: OpenInAppApi,
    private val sharedPreference: OpenInAppSharedPreference
) {

    suspend fun fetchDashboardData(): Result<DashboardData> {
        return try {
            val token = sharedPreference.getString(Constants.TOKEN)
            val response = openInAppApi.fetchDashboardData("bearer $token")
            if (response.isSuccessful && response.body()?.status == true) {
                Result.Success(response.body()!!)
            }else {
                Result.Error("Call Failed")
            }
        }catch (ex: Exception) {
            Result.Error(ex.toString())
        }
    }
}