package com.will.ripperview_demo.util

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * 屏幕测量和获取屏幕大小，获取屏幕像素工具类
 * 双重校验锁式
 */
class MeasureUtil  private constructor(context: Context){
    //这是屏幕显示宽高
    private var mDisplayWidth = 0
    private var mDisplayHeight = 0

    companion object {
        //这里是设计的参考的宽高
        private const val STANDARD_WIDTH : Float = 720f
        private const val STANDARD_HEIGHT : Float = 1280f
        @Volatile
        private  var instance: MeasureUtil? = null
        private val bytes = ByteArray(9)
        @JvmStatic
        fun getInstance(context: Context): MeasureUtil {
            if (instance == null) {
                synchronized(bytes) {
                    if (instance == null) {
                        instance =
                            MeasureUtil(context)
                    }
                }
            }
            return instance!!
        }
    }

    init {
        //获取屏幕的宽高
        if (mDisplayWidth == 0 || mDisplayHeight == 0) {
            val manager : WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val displayMetrics : DisplayMetrics = DisplayMetrics()
                manager.defaultDisplay.getMetrics(displayMetrics)
                if (displayMetrics.widthPixels > displayMetrics.heightPixels){
                    //是横屏的情况下
                    mDisplayWidth = displayMetrics.heightPixels
                    mDisplayHeight = displayMetrics.widthPixels
                } else{
                    mDisplayWidth = displayMetrics.widthPixels
                    mDisplayHeight = displayMetrics.heightPixels - getStatusBarHeight(context)
                }
        }
    }

    /**
     * 获取状态栏的高度
     */
    public fun getStatusBarHeight(context: Context):Int{
        val resId : Int = context.resources.getIdentifier("status_bar_height","dimen","android")
        if (resId > 0){
            return context.resources.getDimensionPixelSize(resId)
        }

        return 0
    }

    /**
     * 获取水平方向的缩放比例
     */
    public fun getHorizontalScale():Float{
        return mDisplayWidth / STANDARD_WIDTH
    }

    /**
     * 获取垂直方向的缩放比例
     */
    public fun getVerticalScale():Float{
        return mDisplayHeight / STANDARD_HEIGHT
    }
}