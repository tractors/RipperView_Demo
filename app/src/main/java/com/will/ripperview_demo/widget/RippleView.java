package com.will.ripperview_demo.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.will.ripperview_demo.R;
import com.will.ripperview_demo.view.UIUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自定义组合控件
 */
public class RippleView extends RelativeLayout {
    private Paint paint;

    private int radius;
    private int strokeWidth;
    private int rippleColor;
    private List<RippleCircleView> viewList = new ArrayList<>();
    private AnimatorSet animatorSet;
    //标示位
    private boolean animatorRunning = false;

    public RippleView(Context context) {
        this(context,null);
    }

    public RippleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    /**
     * 初始化
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleView);
        int rippleType = typedArray.getInt(R.styleable.RippleView_ripple_anim_type,0);
        //水波纹的大小
        radius = typedArray.getInteger(R.styleable.RippleView_radius,54);
        //线宽
        strokeWidth = typedArray.getInteger(R.styleable.RippleView_strokeWidth,2);
        //颜色
        rippleColor = typedArray.getColor(R.styleable.RippleView_ripple_anim_color, ContextCompat.getColor(context,R.color.rippleColor));
        //设置画笔的线宽
        paint.setStrokeWidth(UIUtils.getInstance().getWidth(strokeWidth));
        if (rippleType == 0){
            paint.setStyle(Paint.Style.FILL);
        } else {
            paint.setStyle(Paint.Style.STROKE);
        }

        paint.setColor(rippleColor);


        LayoutParams rippleParams = new LayoutParams(UIUtils.getInstance().getWidth(radius+strokeWidth),UIUtils.getInstance().getHeight(radius+strokeWidth));
        rippleParams.addRule(CENTER_IN_PARENT,TRUE);
        float maxScale = UIUtils.getInstance().displayMetricsWidth / (float) (UIUtils.getInstance().getWidth(radius + strokeWidth));

        //延迟时间
        int rippleDuration = 3500;//毫秒
        int singleDelay = rippleDuration / 4;//间隔时间(上个波纹到下一个波纹所需的时间)

        //申请一个动画集合
        List<Animator> animatorList = new ArrayList<>();

        //实例化4个波纹
        for (int i = 0; i < 4; i++) {
            //一个波纹
            RippleCircleView rippleCircleView = new RippleCircleView(this);
            addView(rippleCircleView,rippleParams);
            //添加到view集合中
            viewList.add(rippleCircleView);
            //x轴方向的水波动画

            final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(rippleCircleView,"ScaleX",1.0f,maxScale);
            //重复次数
            scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);//无限重复
            //重复模式
            scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);//重新开始
            //设置延迟时间(间隔时间)
            scaleXAnimator.setStartDelay(i * singleDelay);
            //总的水波时间
            scaleXAnimator.setDuration(rippleDuration);
            //放入动画集合中
            animatorList.add(scaleXAnimator);

            //y轴方向的水波动画
            final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(rippleCircleView,"ScaleY",1.0f,maxScale);
            //重复次数
            scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);//无限重复
            //重复模式
            scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);//重新开始
            //设置延迟时间(间隔时间)
            scaleYAnimator.setStartDelay(i * singleDelay);
            //总的水波时间
            scaleYAnimator.setDuration(rippleDuration);
            //放入动画集合中
            animatorList.add(scaleYAnimator);

            //alpha 透明度
            final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(rippleCircleView,"Alpha",1.0f,0.0f);
            //重复次数
            alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);//无限重复
            //重复模式
            alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);//重新开始
            //设置延迟时间(间隔时间)
            alphaAnimator.setStartDelay(i * singleDelay);
            //总的水波时间
            alphaAnimator.setDuration(rippleDuration);
            //放入动画集合中
            animatorList.add(alphaAnimator);
        }

        animatorSet = new AnimatorSet();
        //设置插值器
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(animatorList);
    }

    /**
     * 启动动画
     */
    public void startRippleAnimation(){
        if (!animatorRunning){
            //有里面到外面逐一开始
            for (RippleCircleView rippleCircleView : viewList) {
                rippleCircleView.setVisibility(VISIBLE);
                animatorSet.start();
            }


            animatorRunning = true;
        }
    }

    /**
     * 停止动画
     */
    public void stopRippleAnimation(){
        if (animatorRunning){
            //做反序排列，有外面到里面逐一关闭
            Collections.reverse(viewList);
            for (RippleCircleView rippleCircleView : viewList) {
                rippleCircleView.setVisibility(INVISIBLE);
                animatorSet.end();
                animatorSet.cancel();
            }


            animatorRunning = false;
        }
    }

    /**
     * 获取线宽
     * @return
     */
    public int getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * 获取画笔
     * @return
     */
    public Paint getPaint() {
        return paint;
    }

    public boolean isAnimatorRunning() {
        return animatorRunning;
    }
}