package com.will.ripperview_demo

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * 刘海屏幕设置实例
 */
class DisplayCutoutActivity : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //1。判断手机厂商 2。判断手机是否有刘海 3.让内容区域延伸进刘海 4. 设置成沉浸式 控件是否避开刘海区 5.获取刘海的高度
        //设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val window = window
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        //判断是否是刘海屏
        val hasDisplayCutout : Boolean = hasDisplayCutout(window)

        if (hasDisplayCutout){
            //让内容区域延伸进刘海
            val params :WindowManager.LayoutParams = window.attributes

            //LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT //全屏模式，内容下移，非全屏模式不受影响
            //LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES //允许内容延伸进刘海区
            //LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER //不允许内容延伸进刘海区

            params.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes  = params
            //设置成沉浸式
            val flags:Int = (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN) //全屏

            var visibility = window.decorView.systemUiVisibility
            visibility = visibility or flags

            window.decorView.systemUiVisibility = visibility
        }


        setContentView(R.layout.activity_display_cutout)

        val btn : Button = findViewById(R.id.btn)
        var params : ConstraintLayout.LayoutParams = btn.layoutParams as ConstraintLayout.LayoutParams

        params.topMargin = heightForDisplayCutout()

        btn.layoutParams = params
    }


    @RequiresApi(Build.VERSION_CODES.P)
    private fun hasDisplayCutout(window: Window): Boolean {
        var displayCutout : DisplayCutout? = null
        val rootView : View = window.decorView
        val insets:WindowInsets? = rootView.rootWindowInsets
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && insets != null){
            displayCutout = insets.displayCutout
            if (displayCutout != null){
                if (displayCutout.boundingRects != null && displayCutout.boundingRects.size > 0 && displayCutout.safeInsetTop > 0){
                    return true
                }
            }
        }

        return false
    }


    /**
     * 通常情况下，状态栏的高度就是刘海的高度
     */
    fun heightForDisplayCutout() : Int{
        val resId : Int = resources.getIdentifier("status_bar_height","dimen","android")
        if (resId > 0){
            return resources.getDimensionPixelSize(resId)
        }

        return 96
    }
}