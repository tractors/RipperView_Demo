package com.will.ripperview_demo.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class ForkView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), ValueAnimator.AnimatorUpdateListener{

    private val mPaint: Paint
    private val dst1: Path = Path()
    private val dst2: Path = Path()
    private val dst3: Path = Path()
    private var v1: Float = 0.0f
    private var v2: Float = 0.0f
    private var v3: Float = 0.0f
    private val mPath1: Path = Path()
    private val mPath2: Path = Path()
    private val mPath3: Path = Path()
    private val mPathMeasure: PathMeasure = PathMeasure()
    private val valueAnimator1: ValueAnimator = ValueAnimator.ofFloat(0f,1f)
    private val valueAnimator2: ValueAnimator = ValueAnimator.ofFloat(0f,1f)
    private val valueAnimator3: ValueAnimator = ValueAnimator.ofFloat(0f,1f)
    private val mLineWidth : Float = 10f

    init {
        valueAnimator1.duration = 1000
        valueAnimator1.start()

        valueAnimator2.duration = 500

        valueAnimator3.duration = 500

        valueAnimator1.addUpdateListener(this)
        valueAnimator2.addUpdateListener(this)
        valueAnimator3.addUpdateListener(this)

        mPaint = Paint()

        mPaint.isAntiAlias = true
        mPaint.strokeWidth = mLineWidth
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPath1.addCircle(width / 2f,height / 2f,width / 2f - mLineWidth / 2f,Path.Direction.CW)
        mPath2.moveTo(width * 0.3f, height * 0.3f)
        mPath2.lineTo(width * 0.7f, height * 0.7f)
        mPath3.moveTo(width * 0.7f, height * 0.3f)
        mPath3.lineTo(width * 0.3f, height * 0.7f)
        mPathMeasure.setPath(mPath1,false)
        mPathMeasure.getSegment(0f,v1 * mPathMeasure.length,dst1,true)
        canvas?.drawPath(dst1,mPaint)

        if (v1 == 1f) {
            mPathMeasure.nextContour();
            mPathMeasure.setPath(mPath2, false);
            mPathMeasure.getSegment(0f, v2 * mPathMeasure.length, dst2, true);
            canvas?.drawPath(dst2, mPaint);
        }
        if (v2 == 1f) {
            mPathMeasure.nextContour();
            mPathMeasure.setPath(mPath3, false);
            mPathMeasure.getSegment(0f, v3 * mPathMeasure.length, dst3, true);
            canvas?.drawPath(dst3, mPaint);
        }
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) {
        if (animation!! == valueAnimator1) {
            v1 = animation.animatedValue as Float
            invalidate()
            if (v1 == 1f) {
                valueAnimator2.start()
            }
        } else if (animation == valueAnimator2) {
            v2 = animation.animatedValue as Float
            invalidate()
            if (v2 == 1f) {
                valueAnimator3.start()
            }
        } else if (animation == valueAnimator3) {
            v3 = animation.animatedValue as Float
            invalidate()
        }
    }
}