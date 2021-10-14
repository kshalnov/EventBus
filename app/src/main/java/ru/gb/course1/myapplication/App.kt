package ru.gb.course1.myapplication

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment

class App : Application() {
    val counterBus = EventBus()
}

val Fragment.app: App
    get() = requireContext().app

val Context.app: App
    get() = applicationContext as App
