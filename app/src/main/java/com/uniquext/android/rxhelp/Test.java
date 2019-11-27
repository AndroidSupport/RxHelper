package com.uniquext.android.rxhelp;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.uniquext.android.rxlifecycle.RxLifecycle;
import com.uniquext.android.rxlifecycle.event.ActivityEvent;

import java.util.HashMap;

import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;

public class Test implements LifecycleObserver {

    private final BehaviorSubject<ActivityEvent> activityEventSubject = BehaviorSubject.create();

    public <Upstream> ObservableTransformer<Upstream, Upstream> bindUntilEvent(final ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(activityEventSubject, event);
    }

    private static final String TAG = "MyObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreated() {
        Log.d(TAG, "onCreated: ");
        activityEventSubject.onNext(ActivityEvent.CREATE);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.d(TAG, "onStart: ");
        activityEventSubject.onNext(ActivityEvent.START);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.d(TAG, "onResume: ");
        activityEventSubject.onNext(ActivityEvent.RESUME);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.d(TAG, "onPause: ");
        activityEventSubject.onNext(ActivityEvent.PAUSE);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.d(TAG, "onStop: ");
        activityEventSubject.onNext(ActivityEvent.STOP);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        Log.d(TAG, "onDestory: ");
        activityEventSubject.onNext(ActivityEvent.DESTROY);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny() {//此方法可以有参数，但类型必须如两者之一(LifecycleOwner owner,Lifecycle.Event event)
        Log.d(TAG, "onAny: ");
    }

}
