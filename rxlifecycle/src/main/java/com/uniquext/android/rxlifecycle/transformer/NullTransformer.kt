package com.uniquext.android.rxlifecycle.transformer

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer

internal class NullTransformer<Upstream> : ObservableTransformer<Upstream, Upstream> {
    override fun apply(upstream: Observable<Upstream>): ObservableSource<Upstream> {
        return upstream
    }
}