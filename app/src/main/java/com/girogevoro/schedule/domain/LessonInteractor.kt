package com.girogevoro.schedule.domain

import com.girogevoro.schedule.CURRENT_TIME
import com.girogevoro.schedule.data.lessons_repo.LessonsRepo
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class LessonInteractor(private val repo: LessonsRepo) {

    fun getLessonsByDate(searchDate: LocalDate) = repo
        .getLessonsByDate(searchDate)
        .map { it ->
            it.filter { it.date == searchDate }
        }
        .map { it ->
            it.sortedBy { it.timeStart }
        }
        .map {
            var index = it
                .filter { lesson -> lesson.timeStart <= CURRENT_TIME }
                .lastIndex
            if (index == -1) index = 0

            it[index].isCurrent = true
            it
        }
}