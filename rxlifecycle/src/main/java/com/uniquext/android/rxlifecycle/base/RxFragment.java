package com.uniquext.android.rxlifecycle.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.uniquext.android.rxlifecycle.RxLifecycle;
import com.uniquext.android.rxlifecycle.event.FragmentEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.BehaviorSubject;

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
 * @date 2019/4/25  16:52
 */
public abstract class RxFragment extends Fragment {

    private final BehaviorSubject<FragmentEvent> fragmentEventSubject = BehaviorSubject.create();

    public <Upstream> ObservableTransformer<Upstream, Upstream> bindUntilEvent(final FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(fragmentEventSubject, event);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentEventSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentEventSubject.onNext(FragmentEvent.CREATE);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentEventSubject.onNext(FragmentEvent.VIEW_CREATE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentEventSubject.onNext(FragmentEvent.ACTIVITY_CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();
        fragmentEventSubject.onNext(FragmentEvent.START);
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentEventSubject.onNext(FragmentEvent.RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();
        fragmentEventSubject.onNext(FragmentEvent.PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        fragmentEventSubject.onNext(FragmentEvent.STOP);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentEventSubject.onNext(FragmentEvent.VIEW_DESTROY);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentEventSubject.onNext(FragmentEvent.DESTROY);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentEventSubject.onNext(FragmentEvent.DETACH);
    }
}
