package com.girogevoro.schedule.domain

import com.girogevoro.schedule.CURRENT_DATE
import com.girogevoro.schedule.data.homework_repo.HomeworkRepo
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.Period

class HomeworkInteractor(private val repo: HomeworkRepo) {

    fun getHomeworkByDate(searchDate: LocalDate) = repo
        .getHomeworkByDate(searchDate)
        .map { it ->
            it.filter { it.date >= searchDate }
        }
        .map { it ->
            it.forEach {
                it.daysLeft = Period.between(CURRENT_DATE, it.date).days
            }
            it.sortedBy { it.date }
        }
}