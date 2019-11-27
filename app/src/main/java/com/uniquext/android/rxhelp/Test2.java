package com.uniquext.android.rxhelp;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.uniquext.android.rxlifecycle.event.ActivityEvent;

import java.util.HashMap;

import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;

public class Test2 {



    public static void onCreate() {
    }
    public static void onStart() {

    }
    public static void onResume() {

    }
    public static <Upstream> ObservableTransformer<Upstream, Upstream> onPause(LifecycleOwner lifecycleOwner) {
        return map.get(lifecycleOwner).bindUntilEvent(ActivityEvent.PAUSE);
    }
    public static void onStop() {

    }

    public static <Upstream> ObservableTransformer<Upstream, Upstream> onDestroy(LifecycleOwner lifecycleOwner) {
        return map.get(lifecycleOwner).bindUntilEvent(ActivityEvent.STOP);
    }





    public static void bind(LifecycleOwner lifecycleOwner) {
        map.put(lifecycleOwner, new Test());
        lifecycleOwner.getLifecycle().addObserver(map.put(lifecycleOwner, new Test()));
    }

    public static void unbind(LifecycleOwner lifecycleOwner) {
        lifecycleOwner.getLifecycle().removeObserver(map.get(lifecycleOwner));
        map.remove(lifecycleOwner);
    }


    private static final HashMap<LifecycleOwner, Test> map = new HashMap<>();
}
