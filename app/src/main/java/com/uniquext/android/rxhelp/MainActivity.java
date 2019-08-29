package com.uniquext.android.rxhelp;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.uniquext.android.rxhelp.compose.NetworkResponseBean;
import com.uniquext.android.rxhelp.compose.SimpleTransformerUtil;
import com.uniquext.android.rxlifecycle.base.RxAppCompatActivity;
import com.uniquext.android.rxlifecycle.event.ActivityEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class MainActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        life();

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


    private void life(){
        Observable
                .merge(Observable.just(1L), Observable .interval(5 * 1000L, TimeUnit.MILLISECONDS))
//                .just("aaaaaa")
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
