package com.uniquext.android.rxlifecycle.feature

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import io.reactivex.ObservableTransformer
import java.util.concurrent.ConcurrentHashMap

object RxLifecycle {

    private val lifecycleMap: ConcurrentHashMap<LifecycleOwner, RxLifecycleObserver> = ConcurrentHashMap()

    fun <Upstream> untilCreate(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_CREATE)
        }
    }

    fun <Upstream> untilStart(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_START)
        }
    }

    fun <Upstream> untilResume(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_RESUME)
        }
    }

    fun <Upstream> untilPause(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_PAUSE)
        }
    }

    fun <Upstream> untilStop(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_STOP)
        }
    }

    fun <Upstream> untilDestroy(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_DESTROY)
        }
    }

    fun bind(lifecycleOwner: LifecycleOwner) {
        val lifecycleObserver = RxLifecycleObserver()
        lifecycleMap[lifecycleOwner] = lifecycleObserver
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
    }

    fun unbind(lifecycleOwner: LifecycleOwner) {
        val lifecycleObserver = lifecycleMap.remove(lifecycleOwner)
        if (lifecycleObserver != null) {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }
}