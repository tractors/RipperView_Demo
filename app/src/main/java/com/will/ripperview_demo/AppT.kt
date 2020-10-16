package com.will.ripperview_demo

import android.app.Application
import com.will.ripperview_demo.view.UIUtils

class AppT : Application() {

    override fun onCreate() {
        super.onCreate()
        UIUtils.getInstance(this)
    }
}