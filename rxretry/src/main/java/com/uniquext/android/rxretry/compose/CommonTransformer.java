package com.uniquext.android.rxretry.compose;

import com.uniquext.android.rxretry.retry.ObservableRetry;
import com.uniquext.android.rxretry.retry.RetryConfig;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
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
 * @date 2019/4/23  10:19
 */
public class CommonTransformer<Stream> implements ObservableTransformer<Stream, Stream> {

    private final Consumer<Disposable> DEFAULT_ON_SUBSCRIBE = disposable -> {
    };
    private final Action DEFAULT_ON_TERMINATE = () -> {
    };
    private final Function<Stream, ObservableSource<Stream>> DEFAULT_ON_RESULT_FORMAT = Observable::just;
    private final Function<Throwable, ObservableSource<Stream>> DEFAULT_ON_ERROR_FORMAT = Observable::error;

    protected Action mOnTerminate;
    protected Consumer<Disposable> mOnSubscribe;
    protected Function<Stream, ObservableSource<Stream>> mOnResultFormat;
    protected Function<Throwable, ObservableSource<Stream>> mOnErrorResume;
    protected Function<Throwable, RetryConfig> mRetryConfigProvider;

    public CommonTransformer(@Nullable Consumer<Disposable> onSubscribe,
                             @Nullable Function<Stream, ObservableSource<Stream>> onResultFormat,
                             @Nullable Function<Throwable, ObservableSource<Stream>> onErrorResume,
                             @Nullable Action onTerminate,
                             @Nullable Function<Throwable, RetryConfig> retryConfigHandler) {
        this.mOnSubscribe = onSubscribe;
        this.mOnResultFormat = onResultFormat;
        this.mOnErrorResume = onErrorResume;
        this.mOnTerminate = onTerminate;
        this.mRetryConfigProvider = retryConfigHandler;
    }

    @Override
    public ObservableSource<Stream> apply(Observable<Stream> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(mOnSubscribe == null ? DEFAULT_ON_SUBSCRIBE : mOnSubscribe)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(mOnResultFormat == null ? DEFAULT_ON_RESULT_FORMAT : mOnResultFormat)
                .onErrorResumeNext(mOnErrorResume == null ? DEFAULT_ON_ERROR_FORMAT : mOnErrorResume)
                .retryWhen(new ObservableRetry(mRetryConfigProvider))
                .doOnTerminate(mOnTerminate == null ? DEFAULT_ON_TERMINATE : mOnTerminate);
    }

}