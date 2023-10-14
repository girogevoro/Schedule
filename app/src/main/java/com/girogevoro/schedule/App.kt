package com.girogevoro.schedule

import android.app.Application
import com.girogevoro.schedule.di.Di
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    Di.viewModuleModule,
                    Di.repoModule
                )
            )
        }
    }
}