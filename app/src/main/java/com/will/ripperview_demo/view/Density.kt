package com.will.ripperview_demo.view

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import android.util.DisplayMetrics

/**
 * 密度适配屏幕
 */
class Density {

    companion object{
        private  const val WIDTH : Float = 360f //参考设备的宽，单位是dp
        private var appDensity : Float = 0f //表示屏幕密度
        private var appScaleDensity : Float = 0f //字体缩放比例，默认（appDensity）
        fun setDensity(application: Application,activity: Activity){
            //获取当前app的屏幕显示信息
            val displayMetrics = application.resources.displayMetrics
            if (appDensity == 0f){
                //初始化赋值操作
                appDensity = displayMetrics.density
                appScaleDensity = displayMetrics.scaledDensity
                //添加字体变化监听回调
                application.registerComponentCallbacks(object : ComponentCallbacks{
                    override fun onLowMemory() {

                    }

                    override fun onConfigurationChanged(newConfig: Configuration) {
                        //字体发生变化，重新对scaleDensity进行赋值
                        if (newConfig != null && newConfig.fontScale > 0){
                            appScaleDensity = application.resources.displayMetrics.scaledDensity
                        }
                    }

                })
            }

            //计算目标值density,scaleDensity,densityDpi
            val targetDensity : Float = displayMetrics.widthPixels / WIDTH //1080 / 360 = 3.0
            val targetScaleDensity : Float = targetDensity * (appScaleDensity / appDensity)

            val targetDensityDpi : Int = (targetDensity * 160).toInt()

            //替换Activity的density,scaleDensity,densityDpi

            val dm : DisplayMetrics = activity.resources.displayMetrics

            dm.density = targetDensity
            dm.scaledDensity = targetScaleDensity
            dm.densityDpi = targetDensityDpi

        }
    }
}