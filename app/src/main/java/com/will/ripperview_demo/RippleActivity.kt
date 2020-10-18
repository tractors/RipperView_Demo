package com.will.ripperview_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.will.ripperview_demo.view.UIUtils
import com.will.ripperview_demo.widget.RippleView

/**
 * 仿网易云水波纹实例
 */
class RippleActivity : AppCompatActivity() {
    private var imageView : ImageView? = null
    private var rippleView : RippleView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        UIUtils.getInstance(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ripple)
        imageView = findViewById(R.id.imageView)
        //ViewCalculateUtil.setViewLayoutParam(imageView,300,0,0,0,0,0)
        rippleView = findViewById(R.id.layout_RippleAnimation)

        imageView?.setOnClickListener {
            if (rippleView?.isAnimatorRunning!!){
                rippleView?.stopRippleAnimation()
            } else {
                rippleView?.startRippleAnimation()
            }
        }

    }
}