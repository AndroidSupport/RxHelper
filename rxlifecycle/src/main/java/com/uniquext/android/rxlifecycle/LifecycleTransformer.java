package com.uniquext.android.rxlifecycle;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

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
 * @date 2019/4/25  17:17
 */
class LifecycleTransformer<Upstream> implements ObservableTransformer<Upstream, Upstream> {

    private final ObservableSource<?> lifecycleEventSubject;

    LifecycleTransformer(ObservableSource<?> eventSubject) {
        this.lifecycleEventSubject = eventSubject;
    }

    @Override
    public ObservableSource<Upstream> apply(Observable<Upstream> upstream) {
        return upstream.takeUntil(lifecycleEventSubject);
    }
}
