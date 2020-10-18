package com.will.ripperview_demo.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * 自绘控件 成功状态动画
 */
class MarkView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr),ValueAnimator.AnimatorUpdateListener {

    private val mPaint : Paint = Paint()
    //PathMeasure 如果动画中的控件出现部分或者部分动画时，可以采用PathMeasure截取部分动画
    private var mPathMeasure: PathMeasure
    private var mLineWidth = 10.0f
    private val mPath1 : Path
    private val mPath2 : Path
    private val dst1 : Path
    private val dst2 : Path
    private val valueAnimator1 : ValueAnimator
    private val valueAnimator2 : ValueAnimator
    private var v1 : Float = 0.0f
    private var v2 : Float = 0.0f
    init {
        //抗锯齿
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = mLineWidth
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.parseColor("#8cc350")

        mPath1 = Path()
        mPath2 = Path()

        dst1 = Path()
        dst2 = Path()

        mPathMeasure = PathMeasure()
        valueAnimator1 = ValueAnimator.ofFloat(0f,1f)
        valueAnimator1.duration = 1000
        valueAnimator1.start()

        valueAnimator2 = ValueAnimator.ofFloat(0f,1f)
        valueAnimator2.duration = 1000
        valueAnimator2.start()

        valueAnimator1.addUpdateListener(this)
        valueAnimator2.addUpdateListener(this)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPath1.addCircle(width.toFloat()/ 2f, height.toFloat() / 2f,width.toFloat() / 2f - mLineWidth.toFloat() / 2f,Path.Direction.CW)
        mPath2.moveTo(width * 0.2f,height * 0.5f)
        mPath2.lineTo(width * 0.45f,height * 0.7f)
        mPath2.lineTo(width * 0.78f,height * 0.38f)

        mPathMeasure.setPath(mPath1,false)
        mPathMeasure.getSegment(0f,v1 * mPathMeasure.length,dst1,true)
        canvas?.drawPath(dst1,mPaint)

        if (v1 == 1f){
            mPathMeasure.nextContour()
            mPathMeasure.setPath(mPath2,false)
            mPathMeasure.getSegment(0f,v2 * mPathMeasure.length,dst2,true)
            canvas?.drawPath(dst2,mPaint)
        }
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) {
        if (animation!! == valueAnimator1){
            v1 = (animation.animatedValue) as Float
            invalidate()
            if (v1 == 1f){
                valueAnimator2.start()
            }
        } else if (animation == valueAnimator2){
            v2 = animation.animatedValue as Float
            invalidate()
        }
    }
}