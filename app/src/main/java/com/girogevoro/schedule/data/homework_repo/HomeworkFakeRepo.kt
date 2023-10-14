package com.girogevoro.schedule.data.homework_repo

import com.girogevoro.schedule.domain.entity.Homework
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate

class HomeworkFakeRepo : HomeworkRepo {

    override fun getHomeworkByDate(date: LocalDate): Flow<List<Homework>> {
        val hw = listOf<Homework>(
            Homework(
                date = LocalDate.now().plusDays(2),
                name = "Literature",
                description = "Read scenes 1.1-1.12 of Master and Margarita.",
                image = "https://static.tildacdn.com/tild3364-3461-4464-b561-613032346536/_.png"
            ),
            Homework(
                date = LocalDate.now().plusDays(5),
                name = "Physics",
                description = "Learn Newton's laws of motions.",
                image = "https://www.tambov.gov.ru/assets/images/press/obrazovanie/nauka(1).jpg"
            ),
            Homework(
                date = LocalDate.now().plusDays(5),
                name = "ForeignLanguage",
                description = "Write an essay on theme: London",
                image = "https://static.tildacdn.com/tild3364-3461-4464-b561-613032346536/_.png"
            ),
        )
        return flow {
            delay(1500)
            emit(hw)
        }
    }

}