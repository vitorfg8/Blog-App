package com.github.vitorfg8.blogapp

import android.app.Application
import com.github.vitorfg8.blogapp.di.apiModule
import com.github.vitorfg8.blogapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(apiModule, appModule))
        }
    }
}