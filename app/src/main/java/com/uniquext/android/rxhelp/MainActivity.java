package com.uniquext.android.rxhelp;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.uniquext.android.rxhelp.compose.NetworkResponseBean;
import com.uniquext.android.rxhelp.compose.SimpleTransformerUtil;
import com.uniquext.android.rxlifecycle.base.RxAppCompatActivity;
import com.uniquext.android.rxlifecycle.event.ActivityEvent;
import com.uniquext.android.rxlifecycle.temp.RxLifecycle;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxLifecycle.INSTANCE.bind(this);

//        life();

//        Observable.just(new NetworkResponseBean(0, "ABC"))
//        Observable.just(new NetworkResponseBean(1, "ABC"))
//        Observable.just(new NetworkResponseBean(2, "ABC"))
//                .compose(SimpleTransformerUtil.transformer(findViewById(R.id.textview)))
//                .subscribe(new Observer<NetworkResponseBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(NetworkResponseBean networkResponseBean) {
//                        Toast.makeText(MainActivity.this, "请求成功了", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(MainActivity.this, "取消了重试", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  必须在super后面   因为rxjava生命周期可能绑定监听onDestroy
        //  而rxjava的监听实现依赖于lifecycle
//        Test2.unbind(this);
//        com.uniquext.android.rxlifecycle.temp.Test2.INSTANCE.bind();
        RxLifecycle.INSTANCE.unbind(this);

    }

    private void life(){
        Observable
                .merge(Observable.just(1L), Observable .interval(5 * 1000L, TimeUnit.MILLISECONDS))
//                .just("aaaaaa")
//                .compose(bindUntilEvent(ActivityEvent.STOP))
//                .compose(Test2.onDestroy(this))
                .compose(RxLifecycle.INSTANCE.untilStop(this))
                .compose(new ObservableTransformer<Long, Long>() {
                    @Override
                    public ObservableSource<Long> apply(Observable<Long> upstream) {
                        return upstream;
                    }
                })
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
