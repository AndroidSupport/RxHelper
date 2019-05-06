package com.uniquext.android.rxhelp;

import android.os.Bundle;
import android.util.Log;

import com.uniquext.android.rxlifecycle.base.RxAppCompatActivity;
import com.uniquext.android.rxlifecycle.event.ActivityEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .compose(bindUntilEvent(ActivityEvent.STOP))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i("####", "onNext " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("####", "onComplete ");
                    }
                });
    }
}
