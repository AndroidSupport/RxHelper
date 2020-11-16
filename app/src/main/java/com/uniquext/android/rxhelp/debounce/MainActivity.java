package com.uniquext.android.rxhelp.debounce;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.uniquext.android.rxhelp.BaseActivity;
import com.uniquext.android.rxhelp.R;
import com.uniquext.android.rxhelp.SecondActivity;
import com.uniquext.android.rxhelp.compose.NetworkResponseBean;
import com.uniquext.android.rxhelp.compose.SimpleTransformerUtil;
import com.uniquext.android.rxlifecycle.RxLifecycle;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DefaultObserver;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textview);
        textView.setText("First Activity");
        textView.setOnClickListener(v -> test());



    }

    private void test() {
        mEmitter.onNext(true);
    }

    //  自动创建
    private ObservableEmitter<Object> mEmitter;
    //  自动创建
    private void temp() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                mEmitter = emitter;
            }
        })
                .throttleFirst(2L, TimeUnit.SECONDS)
//                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new DefaultObserver<Object>() {
                    @Override
                    public void onNext(Object o) {
                        _test();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    //  自动创建
    private void _test() {
        Log.e("####", "产生了点击事件");
    }

}
