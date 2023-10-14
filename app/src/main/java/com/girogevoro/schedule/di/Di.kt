package com.girogevoro.schedule.di

import com.girogevoro.schedule.data.homework_repo.HomeworkFakeRepo
import com.girogevoro.schedule.data.homework_repo.HomeworkRepo
import com.girogevoro.schedule.data.lessons_repo.LessonsFakeRepo
import com.girogevoro.schedule.data.lessons_repo.LessonsRepo
import com.girogevoro.schedule.domain.HomeworkInteractor
import com.girogevoro.schedule.domain.LessonInteractor
import com.girogevoro.schedule.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Di {
    val viewModuleModule = module {
        viewModel { HomeViewModel(lessonInteractor = get(), homeworkInteractor = get()) }
    }
    val repoModule = module {
        // interactors
        single<LessonInteractor> { LessonInteractor(repo = get()) }
        single<HomeworkInteractor> { HomeworkInteractor(repo = get()) }

        // data sources
        single<LessonsRepo> { LessonsFakeRepo() }
        single<HomeworkRepo> { HomeworkFakeRepo() }
    }
}