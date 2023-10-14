package com.girogevoro.schedule

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

val CURRENT_DATE: LocalDate = LocalDate.now()
val CURRENT_TIME: LocalTime = LocalTime.now()
val CURRENT_DATE_TIME: LocalDateTime = LocalDateTime.of(CURRENT_DATE, CURRENT_TIME)
val EXAMS_DATE: LocalDateTime = LocalDateTime.of(CURRENT_DATE.plusDays(6), LocalTime.of(7, 30))