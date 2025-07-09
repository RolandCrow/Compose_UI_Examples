package com.example.combining_composables

import android.app.Application
import com.example.combining_composables.di.DependencyInjector

class JetNotesApplication: Application() {

    lateinit var di: DependencyInjector
    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        di = DependencyInjector(this)
    }
}