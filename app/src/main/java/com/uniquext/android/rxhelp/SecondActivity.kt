package com.uniquext.android.rxhelp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.uniquext.android.rxlifecycle.feature.RxLifecycle
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SecondActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (findViewById<View>(R.id.textview) as TextView).text = "Second Activity"

        test()
    }


    private fun test() {
        lifeA()
        lifeB()
        lifeC()
    }

    private fun lifeA() {
        Observable
                .merge(Observable.just(1L), Observable.interval(5 * 1000L, TimeUnit.MILLISECONDS))
//                .compose(RxLifecycle.untilPause(this))
                .subscribe(object : Observer<Long> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(aLong: Long) {
                        Log.i("#### 2A", "onNext $aLong")
                    }

                    override fun onError(e: Throwable) {}
                    override fun onComplete() {
                        Log.i("#### 2A", "onComplete ")
                    }
                })
    }

    private fun lifeB() {
        Observable
                .merge(Observable.just(1L), Observable.interval(5 * 1000L, TimeUnit.MILLISECONDS))
                .compose(RxLifecycle.untilPause(this))
                .subscribe(object : Observer<Long> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(aLong: Long) {
                        Log.i("#### 2B", "onNext $aLong")
                    }

                    override fun onError(e: Throwable) {}
                    override fun onComplete() {
                        Log.i("#### 2B", "onComplete ")
                    }
                })
    }

    private fun lifeC() {
        Observable
                .merge(Observable.just(1L), Observable.interval(5 * 1000L, TimeUnit.MILLISECONDS))
                .compose(RxLifecycle.untilDestroy(this))
                .subscribe(object : Observer<Long> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(aLong: Long) {
                        Log.i("#### 2C", "onNext $aLong")
                    }

                    override fun onError(e: Throwable) {}
                    override fun onComplete() {
                        Log.i("#### 2C", "onComplete ")
                    }
                })
    }
}