package jt.projects.gbschool.ui.classes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.girogevoro.schedule.CURRENT_DATE
import com.girogevoro.schedule.domain.LessonInteractor
import com.girogevoro.schedule.domain.entity.Lesson
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDate

class ScheduleViewModel(private val lessonInteractor: LessonInteractor) : ViewModel() {
    private var job: Job? = null
    private val resultRecycler = MutableStateFlow<List<Lesson>>(listOf())
    private val isLoading = MutableStateFlow(true)

    init {
        loadData(CURRENT_DATE)
    }

    fun getResultRecycler(): StateFlow<List<Lesson>> {
        return resultRecycler.asStateFlow()
    }

    fun getIsLoading(): StateFlow<Boolean> {
        return isLoading.asStateFlow()
    }

    private fun loadData(date: LocalDate) {
        job?.cancel()
        isLoading.tryEmit(true)

        job = viewModelScope.launch {
            lessonInteractor.getLessonsByDate(date)
                .onEach {
                    resultRecycler.tryEmit(it)
                    isLoading.tryEmit(false)
                }.collect()
        }
    }

}

