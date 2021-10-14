package ru.gb.course1.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        disposable = Observable.interval(3, TimeUnit.SECONDS).subscribe {
//            app.counterBus.post(MinusEvent())
//        }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}