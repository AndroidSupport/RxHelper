# RxLifecycle
android中RxJava的生命周期控制

## 使用
绑定生命周期
```
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxLifecycle.bind(this)
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxLifecycle.unbind(this)
    }
}
```
设置作用域
```
//  compose(RxLifecycle.INSTANCE.untilPause(this))
Observable
        .interval(5 * 1000L, TimeUnit.MILLISECONDS)
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
```

## Dependency
```
implementation 'com.uniquext.androidx:rxlifecycle:x.0.0'
```
