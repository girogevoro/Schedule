package com.girogevoro.schedule.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.girogevoro.schedule.databinding.FragmentHomeBinding
import com.girogevoro.schedule.ui.home.lesson.LessonsAdapter
import com.girogevoro.schedule.utils.ViewBindingFragment
import com.girogevoro.schedule.ui.home.homework.HomeworkAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : ViewBindingFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModel()
    private val classesAdapter by lazy { LessonsAdapter() }
    private val homeworkAdapter by lazy { HomeworkAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeTimerData()
        initUi()
        observeLessonsData()
        observeHomeworkData()
        observeLoadingVisible()
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

    private fun initUi() {
        with(binding.homeSection2.lessonsListRv) {
            adapter = classesAdapter
        }
        with(binding.homeSection3.homeworkListRv) {
            adapter = homeworkAdapter
        }
    }

    private fun observeLessonsData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.getLessonRecycler().collect {
                    binding.homeSection2.tvClassesCount.text = "${it.size} classes today"
                    classesAdapter.setData(it)

                    val curLessonIndex = it.indexOf(it.findLast { lesson ->
                        lesson.isCurrent
                    })
                    if (curLessonIndex != -1) {
                        binding.homeSection2.lessonsListRv.scrollToPosition(curLessonIndex)
                    }
                }
            }
        }
    }

    private fun observeHomeworkData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.getHomeworkRecycler().collect {
                    homeworkAdapter.setData(it)
                }
            }
        }
    }

    private fun observeLoadingVisible() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.getIsLoadingLessons().collect {
                    binding.homeSection2.loadingFrameLayout.root.isVisible = it
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.getIsLoadingHomework().collect {
                    binding.homeSection3.loadingFrameLayout.root.isVisible = it
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