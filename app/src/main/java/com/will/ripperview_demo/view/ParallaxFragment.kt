package com.will.ripperview_demo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * fragment 加载基础布局
 */
class ParallaxFragment : Fragment() {

    //加载布局 了解有哪些需要差异化制定
    //此Fragment上所有的需要实现视差动画视图
    private val parallaxViews : MutableList<View> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle:Bundle? = arguments
        val layoutId : Int = bundle!!.getInt("layoutId")
        val parallaxLayoutInflater : ParallaxLayoutInflater = ParallaxLayoutInflater(inflater,context,this)
        return parallaxLayoutInflater.inflate(layoutId,null)
    }

    fun getParallaxViews():MutableList<View>{
        return parallaxViews
    }
}