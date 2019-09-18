package com.chat.me.rxkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startRxJust();
        startRxInterval()
    }

    private fun startRxInterval() {
        var observable1 = Observable.interval(2, 3, TimeUnit.SECONDS)

        var observer1 = object : Observer<Long> {
            override fun onSubscribe(d: Disposable) {
                Log.d("subscribe", "${d.isDisposed}")
            }

            override fun onComplete() {
                Log.d("comleted", "0")
            }

            override fun onError(e: Throwable) {
                Log.d("error", "${e.message}")
            }

            override fun onNext(t: Long) {
                Log.d("next", "$t")
            }

        }


        observable1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1)
    }

    private fun startRxJust() {
        var observable: Observable<String> = Observable.just("1", "2", "#")
        var observer: Observer<String> = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("SUBSCRIBE", "1")
            }

            override fun onError(e: Throwable) {
                Log.d("ERROR", "0")
            }

            override fun onComplete() {
                Log.d("COMPLETE", "2")
            }

            override fun onNext(t: String) {
                Log.d("NEXT", "$t")
            }

        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)

    }
}
