package com.custard.openinapp.ui.vm

import android.app.Application
import android.icu.util.Calendar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.custard.openinapp.network.RetrofitClient
import com.custard.openinapp.network.data.DashboardData
import com.custard.openinapp.repo.Repository
import com.custard.openinapp.util.OpenInAppSharedPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.custard.openinapp.util.Result

class DashboardVM(application: Application): AndroidViewModel(application) {

    private val openInAppRepository: Repository = Repository(
        RetrofitClient.getOpenInAppAPI(),
        OpenInAppSharedPreference(application.applicationContext)
    )
    val isDataLoading = MutableLiveData(false)
    val data = MutableLiveData<DashboardData>()
    val error = MutableLiveData<String>()

    fun getDashboardData() = viewModelScope.launch(Dispatchers.IO) {
        isDataLoading.postValue(true)
        val result = openInAppRepository.fetchDashboardData()
        when(result) {
            is Result.Error -> {
                isDataLoading.postValue(false)
                error.postValue(result.error)
            }
            is Result.Success -> {
                isDataLoading.postValue(false)
                data.postValue(result.data)
            }
        }
    }

    fun greetUser(): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        return when (hour) {
            in 0..11 -> "Good Morning!"
            in 12..17 -> "Good Afternoon!"
            in 18..23 -> "Good Evening!"
            else -> "Hello!"
        }
    }
}