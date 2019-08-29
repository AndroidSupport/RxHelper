package com.uniquext.android.rxlifecycle;

import com.uniquext.android.rxlifecycle.event.ActivityEvent;
import com.uniquext.android.rxlifecycle.event.FragmentEvent;

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

    public static <Upstream> ObservableTransformer<Upstream, Upstream> bindUntilEvent(Observable<ActivityEvent> eventSubject, ActivityEvent event) {
        return bind(targetEventSubject(eventSubject, event));
    }

    public static <Upstream> ObservableTransformer<Upstream, Upstream> bindUntilEvent(Observable<FragmentEvent> eventSubject, FragmentEvent event) {
        return bind(targetEventSubject(eventSubject, event));
    }

    private static <Upstream, Event> LifecycleTransformer<Upstream> bind(ObservableSource<Event> eventSubject) {
        return new LifecycleTransformer<>(eventSubject);
    }

    private static ObservableSource<ActivityEvent> targetEventSubject(Observable<ActivityEvent> eventSubject, final ActivityEvent event) {
        return eventSubject.filter(e -> e.ordinal() - event.ordinal() >= 0);
    }

    private static ObservableSource<FragmentEvent> targetEventSubject(Observable<FragmentEvent> eventSubject, final FragmentEvent event) {
        return eventSubject.filter(e -> e.ordinal() - event.ordinal() >= 0);
    }
}
