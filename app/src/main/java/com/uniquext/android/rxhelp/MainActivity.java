package com.uniquext.android.rxhelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.uniquext.android.rxhelp.compose.NetworkResponseBean;
import com.uniquext.android.rxhelp.compose.SimpleTransformerUtil;
import com.uniquext.android.rxlifecycle.feature.RxLifecycle;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textview);
        textView.setText("First Activity");
        textView.setOnClickListener(v -> startActivity(new Intent(this, SecondActivity.class)));

        test();

    }


    private void test() {
        lifeA();
        lifeB();
        lifeC();
    }

    private void lifeA() {
        Observable
                .merge(Observable.just(1L), Observable.interval(5 * 1000L, TimeUnit.MILLISECONDS))
                .compose(RxLifecycle.INSTANCE.untilPause(this))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i("#### A", "onNext " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("#### A", "onComplete ");
                    }
                });
    }

    private void lifeB() {
        Observable
                .merge(Observable.just(1L), Observable.interval(5 * 1000L, TimeUnit.MILLISECONDS))
                .compose(RxLifecycle.INSTANCE.untilPause(this))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i("#### B", "onNext " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("#### B", "onComplete ");
                    }
                });
    }

    private void lifeC() {
        Observable
                .merge(Observable.just(1L), Observable.interval(5 * 1000L, TimeUnit.MILLISECONDS))
                .compose(RxLifecycle.INSTANCE.untilDestroy(this))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.i("#### C", "onNext " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("#### C", "onComplete ");
                    }
                });
    }


    private void retry() {
//        Observable.just(new NetworkResponseBean(0, "ABC"))
//        Observable.just(new NetworkResponseBean(1, "ABC"))
        Observable.just(new NetworkResponseBean(2, "ABC"))
                .compose(SimpleTransformerUtil.transformer(findViewById(R.id.textview)))
                .subscribe(new Observer<NetworkResponseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NetworkResponseBean networkResponseBean) {
                        Toast.makeText(MainActivity.this, "请求成功了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "取消了重试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
