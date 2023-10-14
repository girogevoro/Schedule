package com.girogevoro.schedule.data.homework_repo

import com.girogevoro.schedule.domain.entity.Homework
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface HomeworkRepo {
    fun getHomeworkByDate(date: LocalDate): Flow<List<Homework>>
}