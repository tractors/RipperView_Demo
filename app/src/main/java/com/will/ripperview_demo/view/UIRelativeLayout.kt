package com.will.ripperview_demo.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

/**
 * 不同手机屏幕适配工具类
 */
class UIRelativeLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var flag : Boolean = true
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (flag){
            val scaleX : Float = UIUtils.getInstance().horizontalScaleValue
            val scaleY : Float = UIUtils.getInstance().verticalScaleValue

            val childCount : Int = this.childCount

            for (index in 0 until  childCount){
                val child : View = this.getChildAt(index)
                val layoutParams : LayoutParams = child.layoutParams as LayoutParams
                layoutParams.width = (layoutParams.width * scaleX).toInt()
                layoutParams.height = (layoutParams.height * scaleY).toInt()

                layoutParams.leftMargin = (layoutParams.leftMargin * scaleX).toInt()
                layoutParams.rightMargin = (layoutParams.rightMargin * scaleX).toInt()

                layoutParams.topMargin = (layoutParams.topMargin * scaleY).toInt()
                layoutParams.bottomMargin = (layoutParams.bottomMargin * scaleY).toInt()

            }

            flag = false
        }

    }
}