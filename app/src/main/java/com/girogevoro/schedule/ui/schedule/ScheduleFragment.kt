package com.girogevoro.schedule.ui.schedule

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.girogevoro.schedule.CURRENT_DATE
import com.girogevoro.schedule.databinding.FragmentScheduleBinding
import com.girogevoro.schedule.domain.entity.Lesson
import com.girogevoro.schedule.utils.ViewBindingFragment
import jt.projects.gbschool.ui.classes.LessonAdapter
import jt.projects.gbschool.ui.classes.ScheduleViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleFragment : ViewBindingFragment<FragmentScheduleBinding>() {

    val scheduleViewModel: ScheduleViewModel by viewModel()
    private val lessonAdapter by lazy { LessonAdapter(::onItemClicked) }
    private fun onItemClicked(data: Lesson) {
        try {
            val sky = Intent(Intent.ACTION_VIEW)
            sky.data = Uri.parse("skype:${data.teacher}?call&video=true")
            val chosenIntent = Intent.createChooser(sky, "Выберите программу")
            startActivity(chosenIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeViewModelData()
        observeLoadingVisible()
    }

    private fun initUi() {
        binding.tvTodayInfo.text = "Today, ${CURRENT_DATE.dayOfMonth} ${CURRENT_DATE.month}"

        with(binding.rvClassesList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = lessonAdapter
        }
    }

    private fun observeViewModelData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                scheduleViewModel
                    .getResultRecycler()
                    .collect {
                        lessonAdapter.setData(it)

                        val curLessonIndex = it.indexOf(it.findLast { lesson ->
                            lesson.isCurrent
                        })
                        if (curLessonIndex != -1) {
                            binding.rvClassesList.scrollToPosition(curLessonIndex)
                        }
                    }
            }
        }
    }

    private fun observeLoadingVisible() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                scheduleViewModel.getIsLoading().collect {
                    binding.loadingFrameLayout.root.isVisible = it
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ScheduleFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}