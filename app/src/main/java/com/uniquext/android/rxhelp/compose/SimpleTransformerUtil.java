package com.uniquext.android.rxhelp.compose;

import android.widget.Toast;

import com.uniquext.android.rxretry.CommonTransformer;
import com.uniquext.android.rxretry.retry.RetryConfig;

import androidx.appcompat.widget.AppCompatTextView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
 * @date 2019/5/10  18:19
 */
public class SimpleTransformerUtil {

    public static <T extends NetworkResponseBean> CommonTransformer<T> transformer(final AppCompatTextView textView) {
        return new CommonTransformer<>(
                /**
                 * 此处可以在请求开始前做一些前置操作
                 * 比如网络请求的loading
                 */
                new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        textView.append("\n[onSubscribe]可在请求开始前进行一些操作");
                        textView.append("\n假定NetworkResponseBean的code=0时请求结果正常");
                        textView.append("\ncode=0时为Token失效");
                    }
                },
                /**
                 * 此处可以对结果值进行处理
                 * 比如在请求数据时服务器可能有各种返回值
                 * Demo中模拟为登录Token失效
                 */
                new Function<T, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(T t) throws Exception {
                        if (t.code == 0) {
                            return Observable.just(t);
                        } else if (t.code == 1) {
                            return Observable.error(new TokenException());
                        } else {
                            return Observable.error(new UnknownError());
                        }
                    }
                },
                /**
                 * 此处可以对异常进行处理
                 * 比如以上的Token失效可以让用户返回登录页
                 */
                new Function<Throwable, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof TokenException) {
                            Toast.makeText(textView.getContext(), "用Toast替代跳转登录页", Toast.LENGTH_SHORT).show();
                            return Observable.empty();
                        } else {
                            return Observable.error(throwable);
                        }
                    }
                },
                /**
                 * 此处在请求结束后进行收尾
                 * 不管是请求成功还是失败都会执行
                 */
                new Action() {
                    @Override
                    public void run() throws Exception {
                        textView.append("\n请求结束了");
                    }
                },
                /**
                 * 此处在请求异常时执行
                 */
                new Function<Throwable, RetryConfig>() {
                    @Override
                    public RetryConfig apply(Throwable throwable) throws Exception {
                        return new RetryConfig(RxDialog.showErrorDialog(textView.getContext(), "请求数据异常，是否重新请求？"));
                    }
                }
        );
    }

    public static class TokenException extends Throwable {

    }

}
