package com.girogevoro.schedule.data.lessons_repo

import com.girogevoro.schedule.domain.entity.Lesson
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface LessonsRepo {
    fun getLessonsByDate(date: LocalDate): Flow<List<Lesson>>
}