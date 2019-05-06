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
 * @date 2019/4/25  17:05
 */
public class RxLifecycle {

    public static <Upstream, Event> ObservableTransformer<Upstream, Upstream> bindUntilEvent(Observable<Event> eventSubject, Event event) {
        return bind(takeUntilEvent(eventSubject, event));
    }

    private static <Upstream, Event> LifecycleTransformer<Upstream> bind(ObservableSource<Event> eventSubject) {
        return new LifecycleTransformer<>(eventSubject);
    }

    private static <Event> ObservableSource<Event> takeUntilEvent(Observable<Event> eventSubject, final Event event) {
        return eventSubject.filter(e -> e.equals(event));
    }
}
