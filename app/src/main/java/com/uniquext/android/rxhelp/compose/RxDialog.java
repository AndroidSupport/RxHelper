package com.uniquext.android.rxhelp.compose;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
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
 * @date 2019/4/30  14:13
 */
public class RxDialog {

    public static Single<Boolean> showErrorDialog(final Context context, String message) {
        return Single.create(emitter ->
                new AlertDialog.Builder(context)
                        .setTitle("错误")
                        .setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton("重试", (dialog, which) -> emitter.onSuccess(true))
                        .setNegativeButton("取消", (dialog, which) -> emitter.onSuccess(false)).show()
        );
    }
}
