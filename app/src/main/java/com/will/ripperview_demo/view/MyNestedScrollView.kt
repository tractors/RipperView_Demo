package com.will.ripperview_demo.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView

/**
 * 自定义布局滚动
 */
class MyNestedScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    private var scrollInterface : ScrollInterface? = null

    public interface ScrollInterface{
        fun onScrollChange(scrollX : Int,scrollY:Int,oldScrollX :Int,oldScrollY :Int)
    }

    public fun setOnMyScrollChangeListener(t:ScrollInterface){
        this.scrollInterface = t
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        if (scrollInterface != null){
            scrollInterface!!.onScrollChange(l,t,oldl,oldt)
        }
        super.onScrollChanged(l, t, oldl, oldt)
    }
}