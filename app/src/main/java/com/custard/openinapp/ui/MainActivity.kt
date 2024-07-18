package com.custard.openinapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.fragment.app.activityViewModels
import androidx.navigation.ui.setupWithNavController
import com.custard.openinapp.R
import com.custard.openinapp.databinding.ActivityMainBinding
import com.custard.openinapp.ui.vm.DashboardVM
import com.custard.openinapp.util.Constants
import com.custard.openinapp.util.OpenInAppSharedPreference

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<DashboardVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getDashboardData()

        // Test IMPL
        setToken()
        val navView: BottomNavigationView = binding.navView.apply {
            background = null
            menu.getItem(2).isEnabled = false
        }
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
    }

    private fun setToken() {
        val sharedPreference = OpenInAppSharedPreference(this)
        sharedPreference.setString(Constants.TOKEN, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI")
    }
}