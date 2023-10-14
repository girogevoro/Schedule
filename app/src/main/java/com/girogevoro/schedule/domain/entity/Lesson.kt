package com.girogevoro.schedule.domain.entity

import java.time.LocalDate
import java.time.LocalTime

data class Lesson(
    val date: LocalDate = LocalDate.now(),
    val timeStart: LocalTime,
    val timeEnd: LocalTime,
    val name: String,
    val teacher: String,
    val description: String = "",
    val image: String = "https://thumbs.dreamstime.com/b/corel-pictogramm-141634577.jpg",
    val isOpenIn: Boolean = false,
    val isAdditional: Boolean = false,
    var isCurrent: Boolean = false
)
