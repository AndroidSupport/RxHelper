package com.uniquext.android.rxlifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.uniquext.android.rxlifecycle.wrap.EventBinder
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.BehaviorSubject

class RxLifecycleObserver : LifecycleObserver {

    private val lifecycleEventSubject: BehaviorSubject<Lifecycle.Event> = BehaviorSubject.create()

    fun <Upstream> bindUntilEvent(event: Lifecycle.Event): ObservableTransformer<Upstream, Upstream> {
        return EventBinder.bindUntilEvent(lifecycleEventSubject, event)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreated() {
        lifecycleEventSubject.onNext(Lifecycle.Event.ON_CREATE)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        lifecycleEventSubject.onNext(Lifecycle.Event.ON_START)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        lifecycleEventSubject.onNext(Lifecycle.Event.ON_RESUME)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        lifecycleEventSubject.onNext(Lifecycle.Event.ON_PAUSE)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        lifecycleEventSubject.onNext(Lifecycle.Event.ON_STOP)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        lifecycleEventSubject.onNext(Lifecycle.Event.ON_DESTROY)
    }
}