package com.custard.openinapp.ui.frag.topLink

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.custard.openinapp.R
import com.custard.openinapp.databinding.FragmentProfileBinding
import com.custard.openinapp.databinding.FragmentTopLinkBinding
import com.custard.openinapp.ui.adapter.LinkAdapter
import com.custard.openinapp.ui.vm.DashboardVM

class TopLinkFragment : Fragment() {

    private var _binding: FragmentTopLinkBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<DashboardVM>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopLinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterr = LinkAdapter { link ->
            val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", link)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(requireContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show()
        }
        binding.topLinkRV.apply {
            adapter = adapterr
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.data.observe(viewLifecycleOwner) {
            adapterr.data = it.data.topLinks
            adapterr.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}