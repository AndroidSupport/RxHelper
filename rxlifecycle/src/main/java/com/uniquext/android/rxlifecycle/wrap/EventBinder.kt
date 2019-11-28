package com.uniquext.android.rxlifecycle.wrap

import androidx.lifecycle.Lifecycle
import com.uniquext.android.rxlifecycle.transformer.LifecycleTransformer
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

internal object EventBinder {

    internal fun <Upstream> bindUntilEvent(eventSubject: Observable<Lifecycle.Event>, event: Lifecycle.Event): ObservableTransformer<Upstream, Upstream> {
        return bind(targetEvent(eventSubject, event))
    }

    private fun <Upstream, Event> bind(eventSubject: ObservableSource<Event>): LifecycleTransformer<Upstream> {
        return LifecycleTransformer(eventSubject)
    }

    private fun targetEvent(eventSubject: Observable<Lifecycle.Event>, event: Lifecycle.Event): ObservableSource<Lifecycle.Event> {
        return eventSubject.filter { e: Lifecycle.Event -> e.ordinal - event.ordinal >= 0 }
    }

}