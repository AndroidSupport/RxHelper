package com.uniquext.android.rxretry.retry;

import io.reactivex.Single;

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
 * @date 2019/4/24  10:43
 */
public class RetryConfig {

    private static final Single<Boolean> DEFAULT_HANDLER = Single.just(false);

    private int mTotal;
    private Single<Boolean> mRetryHandler;

    public RetryConfig() {
        this(Integer.MIN_VALUE, DEFAULT_HANDLER);
    }

    public RetryConfig(Single<Boolean> retryHandler) {
        this(Integer.MAX_VALUE, retryHandler);
    }

    public RetryConfig(int total, Single<Boolean> retryHandler) {
        this.mTotal = total;
        this.mRetryHandler = retryHandler;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int mTotal) {
        this.mTotal = mTotal;
    }

    public Single<Boolean> getRetryHandler() {
        return mRetryHandler;
    }

    public void setRetryHandler(Single<Boolean> mRetryHandler) {
        this.mRetryHandler = mRetryHandler;
    }
}
