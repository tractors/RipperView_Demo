package com.will.ripperview_demo.util

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.will.ripperview_demo.view.UIUtils

/**
 * 沉浸式状态栏工具类
 */
class StatusBarUtil {


    companion object{
        fun setTranslucentImageHeader(activity : Activity,alpha :Int,toolbar: Toolbar){
            UIUtils.getInstance(activity)
            //activity沉浸式
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                val window :Window = activity.window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                //设置透明状态栏，这样才能让ContentView 向上
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }

            val statusBarView : StatusBarView = StatusBarView(activity)

            val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,UIUtils.getInstance().getSystemBarHeight(activity))

            statusBarView.layoutParams = params
            statusBarView.setBackgroundColor(Color.argb(alpha,0,0,0))

            val decorView : ViewGroup = activity.window.decorView as ViewGroup

            decorView.addView(statusBarView)

            if (toolbar != null){
                val layoutParams : ViewGroup.MarginLayoutParams = toolbar.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(0,UIUtils.getInstance().getSystemBarHeight(activity),0,0)
            }
        }

        /**
         * 设置状态栏
         */
        fun setStatusBar(activity: Activity,toolBar:View){

            setTranslateStatusBar(activity)

            val decorView : ViewGroup = activity.window.decorView as ViewGroup

            val count :Int = decorView.childCount

            //判断是否已经添加到了statusBarView
            if (count > 0 && decorView.getChildAt(count - 1) is StatusBarView){
                decorView.getChildAt(count - 1).setBackgroundColor(Color.argb(0,0,0,0))
                return
            }

            //绘制一个和状态栏一样高的矩形
            val statusBarView : StatusBarView = StatusBarView(activity)

            val params : LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,UIUtils.getInstance().getSystemBarHeight(activity))

            statusBarView.layoutParams = params
            statusBarView.setBackgroundColor(Color.argb(0,0,0,0))
            decorView.addView(statusBarView)

            if (toolBar != null){
                val layoutParams : ViewGroup.MarginLayoutParams = toolBar.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(0,UIUtils.getInstance().getSystemBarHeight(activity),0,0)
            }
        }

        /**
         * 通过代码来设置状态栏的透明度，及版本的兼容性
         */
        private fun setTranslateStatusBar(activity: Activity){
            //activity沉浸式
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                val window :Window = activity.window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                //设置透明状态栏，这样才能让ContentView 向上
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }
    }
}