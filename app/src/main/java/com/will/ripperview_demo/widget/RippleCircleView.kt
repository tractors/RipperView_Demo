package com.will.ripperview_demo.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

/**
 * 重写draw方法  自定义一个水波纹
 */
class RippleCircleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var rippleView : RippleView? = null

    constructor(rippleView: RippleView) : this(rippleView.context, null) {
        this.rippleView = rippleView
        this.visibility = INVISIBLE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val radius = (min(width,height))/2
        //画一个水波纹
        canvas?.drawCircle(radius.toFloat(),
            radius.toFloat(), (radius- rippleView!!.strokeWidth).toFloat(), rippleView!!.paint)
    }
}