package com.girogevoro.schedule.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girogevoro.schedule.databinding.FragmentHomeBinding
import com.girogevoro.schedule.utils.ViewBindingFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeTimerData()
    }

    private fun observeTimerData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.getResultTimer().collect {
                    with(binding.homeSection1) {
                        tvHour1.text = it[0].toString()
                        tvHour2.text = it[1].toString()
                        tvMin1.text = it[3].toString()
                        tvMin2.text = it[4].toString()
                        tvSec1.text = it[6].toString()
                        tvSec2.text = it[7].toString()
                    }

                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}