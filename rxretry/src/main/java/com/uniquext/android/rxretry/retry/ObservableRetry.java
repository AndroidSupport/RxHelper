package com.uniquext.android.rxretry.retry;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 　 　　   へ　　　 　／|
 * 　　    /＼7　　　 ∠＿/
 * 　     /　│　　 ／　／
 * 　    │　Z ＿,＜　／　　   /`ヽ
 * 　    │　　　 　　ヽ　    /　　〉
 * 　     Y　　　　　   `　  /　　/
 * 　    ｲ●　､　●　　⊂⊃〈　　/
 * 　    ()　 へ　　　　|　＼〈
 * 　　    >ｰ ､_　 ィ　 │ ／／      去吧！
 * 　     / へ　　 /　ﾉ＜| ＼＼        比卡丘~
 * 　     ヽ_ﾉ　　(_／　 │／／           消灭代码BUG
 * 　　    7　　　　　　　|／
 * 　　    ＞―r￣￣`ｰ―＿
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @author UniqueXT
 * @version 1.0
 * @date 2019/4/24  10:42
 */
public class ObservableRetry implements Function<Observable<Throwable>, ObservableSource<?>> {

    private final static long DEFAULT_DELAY = 1L;

    private int mCount = 0;
    private Function<Throwable, RetryConfig> mRetryConfigProvider;

    public ObservableRetry(Function<Throwable, RetryConfig> retryConfigProvider) {
        this.mRetryConfigProvider = retryConfigProvider;
    }

    @Override
    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
        return throwableObservable
                .flatMap(throwable -> {
                    if (mRetryConfigProvider == null) return Observable.error(throwable);
                    RetryConfig retryConfig = mRetryConfigProvider.apply(throwable);
                    if (++mCount > retryConfig.getTotal()) return Observable.error(throwable);
                    return retryConfig.getRetryHandler()
                            .observeOn(Schedulers.io())
                            .flatMapObservable((Function<Boolean, ObservableSource<?>>)
                                    aBoolean -> aBoolean ? Observable.timer(DEFAULT_DELAY, TimeUnit.MILLISECONDS) : Observable.error(throwable)
                            );
                });
    }


}
