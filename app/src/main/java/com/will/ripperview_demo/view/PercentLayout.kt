package com.will.ripperview_demo.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.will.ripperview_demo.R

/**
 * 自定义百分比布局
 */
class PercentLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //获取父容器的宽高
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val count : Int = childCount
        for (index in 0 until count){
            val view = getChildAt(index)
            val params = view.layoutParams

            //如果是百分比布局属性
            if (checkLayoutParams(params)){
                val lp = params as LayoutParams
                var widthPercent:Float = lp.widthPercent
                var heightPercent:Float = lp.heightPercent
                var marginLeftPercent:Float = lp.marginLeftPercent
                var marginRightPercent:Float = lp.marginRightPercent
                var marginTopPercent:Float = lp.marginTopPercent
                var marginBottomPercent:Float = lp.marginBottomPercent

                if (widthPercent > 0){
                    params.width = (widthSize * widthPercent).toInt()
                }

                if (heightPercent > 0){
                    params.height = (heightSize * heightPercent).toInt()
                }

                if (marginLeftPercent > 0){
                    params.leftMargin = (widthSize * marginLeftPercent).toInt()
                }

                if (marginRightPercent > 0){
                    params.rightMargin = (widthSize * marginRightPercent).toInt()
                }

                if (marginTopPercent > 0){
                    params.topMargin = (heightSize * marginTopPercent).toInt()
                }

                if (marginBottomPercent > 0){
                    params.bottomMargin = (heightSize * marginBottomPercent).toInt()
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * 检查是否是同一类型
     */
    override fun checkLayoutParams(p: ViewGroup.LayoutParams?): Boolean {
        return p is LayoutParams
    }

    /**
     * 加载自定义的布局属性
     */
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return LayoutParams(context,attrs)
    }
    companion object{

        class LayoutParams(c: Context, attrs: AttributeSet?) :
            RelativeLayout.LayoutParams(c, attrs) {
            var widthPercent:Float = 0.0f
            var heightPercent:Float = 0.0f
            var marginLeftPercent:Float = 0.0f
            var marginRightPercent:Float = 0.0f
            var marginTopPercent:Float = 0.0f
            var marginBottomPercent:Float = 0.0f

            init {
                //解析自定义属性
                val a : TypedArray = c.obtainStyledAttributes(attrs,R.styleable.PercentLayout)
                widthPercent = a.getFloat(R.styleable.PercentLayout_widthPercent,0f)
                heightPercent = a.getFloat(R.styleable.PercentLayout_heightPercent,0f)
                marginLeftPercent = a.getFloat(R.styleable.PercentLayout_marginLeftPercent,0f)
                marginRightPercent = a.getFloat(R.styleable.PercentLayout_marginRightPercent,0f)
                marginTopPercent = a.getFloat(R.styleable.PercentLayout_marginTopPercent,0f)
                marginBottomPercent = a.getFloat(R.styleable.PercentLayout_marginBottomPercent,0f)

                a.recycle()
            }

        }
    }

}