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
        //  Returns an Observable that emits the items emitted by the source Observable until a second ObservableSource emits an item.
        //  takeUntil将返回一个Observable，发射原始Observable，直到第二个Observable发射一个数据
        return upstream.takeUntil(lifecycleEventSubject);
    }
}
