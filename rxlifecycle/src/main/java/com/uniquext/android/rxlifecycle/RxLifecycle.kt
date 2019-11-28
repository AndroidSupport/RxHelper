package com.uniquext.android.rxlifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.uniquext.android.rxlifecycle.transformer.NullTransformer
import io.reactivex.ObservableTransformer
import java.util.concurrent.ConcurrentHashMap

object RxLifecycle {

    private val lifecycleMap: ConcurrentHashMap<LifecycleOwner, RxLifecycleObserver> = ConcurrentHashMap()

    @JvmStatic
    fun <Upstream> untilCreate(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_CREATE)
        }
    }

    @JvmStatic
    fun <Upstream> untilStart(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_START)
        }
    }

    @JvmStatic
    fun <Upstream> untilResume(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_RESUME)
        }
    }

    @JvmStatic
    fun <Upstream> untilPause(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_PAUSE)
        }
    }

    @JvmStatic
    fun <Upstream> untilStop(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_STOP)
        }
    }

    @JvmStatic
    fun <Upstream> untilDestroy(lifecycleOwner: LifecycleOwner): ObservableTransformer<Upstream, Upstream> {
        return if (lifecycleMap[lifecycleOwner] == null) {
            NullTransformer()
        } else {
            lifecycleMap[lifecycleOwner]!!.bindUntilEvent(Lifecycle.Event.ON_DESTROY)
        }
    }

    @JvmStatic
    fun bind(lifecycleOwner: LifecycleOwner) {
        if(lifecycleMap[lifecycleOwner] == null) {
            val lifecycleObserver = RxLifecycleObserver()
            lifecycleMap[lifecycleOwner] = lifecycleObserver
            lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        }
    }

    @JvmStatic
    fun unbind(lifecycleOwner: LifecycleOwner) {
        val lifecycleObserver = lifecycleMap.remove(lifecycleOwner)
        if (lifecycleObserver != null) {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }
}