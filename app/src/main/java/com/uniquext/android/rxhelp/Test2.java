package com.uniquext.android.rxhelp;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.uniquext.android.rxlifecycle.event.ActivityEvent;

import java.util.HashMap;

import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;

public class Test2 {



    protected void onCreate() {
    }
    protected void onStart() {

    }
    protected void onResume() {

    }
    protected static <Upstream> ObservableTransformer<Upstream, Upstream> onPause(LifecycleOwner lifecycleOwner) {
        return map.get(lifecycleOwner).bindUntilEvent(ActivityEvent.PAUSE);
    }
    protected void onStop() {

    }

    protected static <Upstream> ObservableTransformer<Upstream, Upstream> onDestroy(LifecycleOwner lifecycleOwner) {
        return map.get(lifecycleOwner).bindUntilEvent(ActivityEvent.STOP);
    }





    public static void bind(LifecycleOwner lifecycleOwner) {
        map.put(lifecycleOwner, new Test());
        lifecycleOwner.getLifecycle().addObserver(map.get(lifecycleOwner));
    }

    public static void unbind(LifecycleOwner lifecycleOwner) {
        lifecycleOwner.getLifecycle().removeObserver(map.get(lifecycleOwner));
        map.remove(lifecycleOwner);
    }


    private static final HashMap<LifecycleOwner, Test> map = new HashMap<>();
}
