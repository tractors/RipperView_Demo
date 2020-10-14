package com.will.ripperview_demo.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.abs

/**
 * PathMeasure的测量工具  自绘控件
 */
class PathLoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPath : Path
    private val mPaint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    //PathMeasure 如果动画中的控件出现部分或者部分动画时，可以采用PathMeasure截取部分动画
    private var mPathMeasure: PathMeasure
    private var mLength : Float = 0.0f
    private var dst:Path
    private var mAnimatorValue : Float = 0.0f

    constructor(attrs: AttributeSet?,context: Context,defStyleAttr: Int) : this(context, attrs, defStyleAttr) {

    }
    init {
        //画笔
        mPaint.color = Color.parseColor("#FF4001")
        mPaint.strokeWidth = 10f
        mPaint.style = Paint.Style.STROKE
        //抗锯齿
        mPaint.isAntiAlias = true
        //Path
        mPath = Path()
        mPath.addCircle(300f,300f,100f,Path.Direction.CW) //加入半径为100的圆

        //path的测量工具类
        //闭合 圆，非闭合 直线 boolean 是否闭合
        mPathMeasure = PathMeasure(mPath,true)
        mLength = mPathMeasure.length//不需要自己计算

        //0< distance < length  之间的距离 取值范围

        dst = Path()
        //属性动画
        val valueAnimator = ValueAnimator.ofFloat(0f,1f)
        //设置动画过程的监听
        valueAnimator.addUpdateListener {
            mAnimatorValue = (it.animatedValue) as Float
            invalidate()
        }
        valueAnimator.duration = 2000
        valueAnimator.repeatCount = ValueAnimator.INFINITE //无限循环
        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        dst.reset()
        dst.lineTo(0f,0f)
        val distance : Float = mLength * mAnimatorValue

        val start : Float = (distance - ((0.5 - abs(mAnimatorValue - 0.5)) * mLength)).toFloat()
        //获取片段 false 是从0开始的，true 是从截取的地方开始
        mPathMeasure.getSegment(start,distance,dst,true)

        canvas?.drawPath(dst,mPaint)
    }
}