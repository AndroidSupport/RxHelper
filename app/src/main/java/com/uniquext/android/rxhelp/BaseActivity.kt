package com.uniquext.android.rxhelp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uniquext.android.rxlifecycle.RxLifecycle

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxLifecycle.bind(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        //  以防万一 放super后面   因为rxjava生命周期可能绑定监听onDestroy
        //  而rxjava的监听实现依赖于lifecycle
        RxLifecycle.unbind(this)
    }

}