package com.custard.openinapp.ui.frag.links

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.custard.openinapp.R
import com.custard.openinapp.databinding.FragmentLinksBinding
import com.custard.openinapp.network.data.toLineData
import com.custard.openinapp.network.data.toTopData
import com.custard.openinapp.ui.adapter.TopDataAdapter
import com.custard.openinapp.ui.adapter.ViewPagerAdapter
import com.custard.openinapp.ui.vm.DashboardVM
import com.google.android.material.tabs.TabLayoutMediator

class LinksFragment : Fragment() {

    private var _binding: FragmentLinksBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<DashboardVM>()
    private val dataAdapter = TopDataAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLinksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.greet.text = viewModel.greetUser()
        binding.whatsapp.setOnClickListener {
            val phoneNumber = viewModel.data.value?.message ?: return@setOnClickListener
            val message = "Hello, this is a test message!"
            sendWhatsAppMessage(phoneNumber, message)
        }

        setupAdapters()
        setupTabLayout()
        setupObservers()
    }

    private fun sendWhatsAppMessage(phoneNumber: String, message: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        val url = "https://api.whatsapp.com/send?phone=+91$phoneNumber&text=${Uri.encode(message)}"
        intent.data = Uri.parse(url)
        intent.setPackage("com.whatsapp")
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "WhatsApp is not installed on this device", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupObservers() {
        viewModel.data.observe(viewLifecycleOwner){
            binding.stats.lineChart.data = it.data.overallUrlChart.toLineData(
                ContextCompat.getColor(requireContext(), R.color.blue),
                resources.getDrawable(R.drawable.chart_back)
            )
            binding.stats.lineChart.notifyDataSetChanged()
            binding.stats.lineChart.invalidate()
            dataAdapter.data = it.toTopData(
                a1 = resources.getDrawable(R.drawable.a1),
                a2 = resources.getDrawable(R.drawable.a2),
                a3 = resources.getDrawable(R.drawable.a3)
            )
            dataAdapter.notifyDataSetChanged()
        }
        viewModel.isDataLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.container.visibility = View.INVISIBLE
                binding.loading.visibility = View.VISIBLE
            }else {
                binding.container.visibility = View.VISIBLE
                binding.loading.visibility = View.GONE
            }
        }
    }

    private fun setupAdapters() {
        binding.rv.apply {
            adapter = dataAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.tabViewpager.apply {
            adapter = ViewPagerAdapter(this@LinksFragment)
        }
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabTablayout, binding.tabViewpager)  { tab, position ->
            when(position) {
                0 -> {
                    tab.text = "Recent Links"
                }
                1 -> {
                    tab.text = "Top Links"
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}