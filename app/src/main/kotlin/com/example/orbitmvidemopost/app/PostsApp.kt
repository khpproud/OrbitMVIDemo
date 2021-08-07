package com.example.orbitmvidemopost.app

import android.app.Application
import com.example.orbitmvidemopost.app.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PostsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PostsApp)
            modules(listOf(module()))
        }
    }
}