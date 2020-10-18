package com.will.ripperview_demo.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.will.ripperview_demo.util.MeasureUtil

/**
 * 自定义的控件屏幕缩放适配布局
 */
class ScreenAdapterLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var flag : Boolean = false

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        if (!flag){
            //获取宽高的缩放比例
            val scaleX : Float = MeasureUtil.getInstance(context).getHorizontalScale()
            val scaleY : Float = MeasureUtil.getInstance(context).getVerticalScale()

            //获取子控件的数量
            val count : Int = childCount
            for (index in 0 until  count){
                val child : View = getChildAt(index)
                val param:LayoutParams = child.layoutParams as LayoutParams
                //设置每个子控件的缩放后的值
                param.width = (param.width * scaleX).toInt()
                param.height = (param.height * scaleY).toInt()
                param.leftMargin = (param.leftMargin * scaleX).toInt()
                param.rightMargin = (param.rightMargin * scaleX).toInt()
                param.topMargin = (param.topMargin * scaleY).toInt()
                param.bottomMargin = (param.bottomMargin * scaleY).toInt()
            }

            flag = true
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}