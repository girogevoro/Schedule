package com.girogevoro.schedule.ui.home

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.girogevoro.schedule.CURRENT_DATE_TIME
import com.girogevoro.schedule.EXAMS_DATE
import com.girogevoro.schedule.domain.HomeworkInteractor
import com.girogevoro.schedule.domain.LessonInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Duration

class HomeViewModel(
    private val lessonInteractor: LessonInteractor,
    private val homeworkInteractor: HomeworkInteractor,
) : ViewModel() {
    private val resultTimer = MutableStateFlow<String>("00:00:00")

    init {
        initTimer()
    }

    fun getResultTimer(): StateFlow<String> {
        return resultTimer.asStateFlow()
    }

    private fun initTimer() {
        var different = Duration.between(CURRENT_DATE_TIME, EXAMS_DATE).seconds * 1000

        val countDownTimer = object : CountDownTimer(different, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                var diff = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val hoursInMilli = minutesInMilli * 60
                val daysInMilli = hoursInMilli * 24

                val elapsedDays = diff / daysInMilli
                diff %= daysInMilli

                val elapsedHours = diff / hoursInMilli
                diff %= hoursInMilli

                val elapsedMinutes = diff / minutesInMilli
                diff %= minutesInMilli

                val elapsedSeconds = diff / secondsInMilli

                //   _resultTimer.tryEmit("$elapsedDays days $elapsedHours hs $elapsedMinutes min $elapsedSeconds sec")
                val s = String.format(
                    "%02d:%02d:%02d:%02d",
                    elapsedDays,
                    elapsedHours,
                    elapsedMinutes,
                    elapsedSeconds
                )
                resultTimer.tryEmit(s)
            }

            override fun onFinish() {
                resultTimer.tryEmit("00:00:00")
            }
        }.start()
    }

}