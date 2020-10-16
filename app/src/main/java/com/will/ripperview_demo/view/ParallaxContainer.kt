package com.will.ripperview_demo.view

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.viewpager.widget.ViewPager
import com.nineoldandroids.view.ViewHelper
import com.will.ripperview_demo.R
import com.will.ripperview_demo.bean.ParallaxViewTag

/**
 * 平行控件
 */
class ParallaxContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),ViewPager.OnPageChangeListener {

    private var fragments : MutableList<ParallaxFragment> = arrayListOf()
    private var adapter : ParallaxAdapter? = null

    private var ivMan : ImageView? = null

    fun setIvMan(iv_man : ImageView){
        this.ivMan = iv_man
    }
    //当前控件的宽度
    private var containerWidth : Int = 0
    /**
     * kotlin中可变长参数的写法
     */
    fun setUp(vararg childIds : Int){

        for (element in childIds){
            val fragment : ParallaxFragment = ParallaxFragment()
            val bundle : Bundle = Bundle();
            //fragment中需要加载布局文件的id
            bundle.putInt("layoutId",element)
            fragment.arguments = bundle
            fragments.add(fragment)
        }
        val activity : FragmentActivity = context as FragmentActivity
        //实例化ViewPager
        val vp : ViewPager = ViewPager(context)
        vp.overScrollMode = View.OVER_SCROLL_NEVER
        adapter = ParallaxAdapter(activity.supportFragmentManager ,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments)
        vp.id = R.id.parallax_pager

        vp.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)

        vp.adapter  = adapter
        addView(vp,0)
        vp.setOnPageChangeListener(this)
    }


    override fun onPageScrollStateChanged(state: Int) {
        val annotation : AnimationDrawable  = ivMan?.background as AnimationDrawable
        when (state){
            ViewPager.SCROLL_STATE_DRAGGING->
                annotation.start()
            ViewPager.SCROLL_STATE_IDLE ->
                annotation.stop()
        }
    }

    /**
     * 根据用户滑动的距离,去显示动画
     */
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //position 是接下来的位置
        var inFragment : ParallaxFragment? = null
        //出去的fragment
        var outFragment : ParallaxFragment? = null
        containerWidth = width
        try {
            outFragment = if (position == 0){
                fragments[position]
            } else{
                fragments[position -1]
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            inFragment = fragments[position]
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (outFragment != null){
            val outViews : MutableList<View> = outFragment.getParallaxViews()
            if (outViews != null){
                for (view in outViews){
                    //获取标签，从标签上获取所有的动画参数
                    val tag : ParallaxViewTag =
                        view.getTag(R.id.parallax_view_tag) as ParallaxViewTag ?: continue

                    ViewHelper.setTranslationX(view,containerWidth - positionOffsetPixels * tag.xOut)
                    ViewHelper.setTranslationY(view,containerWidth - positionOffsetPixels * tag.yOut)
                }
            }
        }

        if (inFragment != null){
            val inViews : MutableList<View> = inFragment.getParallaxViews()
            if (inViews != null){
                for (view in inViews){
                    //获取标签，从标签上获取所有的动画参数
                    val tag : ParallaxViewTag =
                        view.getTag(R.id.parallax_view_tag) as ParallaxViewTag ?: continue

                    ViewHelper.setTranslationX(view,0 - positionOffsetPixels * tag.xIn)
                    ViewHelper.setTranslationY(view,0 - positionOffsetPixels * tag.yIn)
                }
            }
        }
    }

    override fun onPageSelected(position: Int) {
        ivMan?.visibility = if(position == (adapter?.count?.minus(1))){
            View.INVISIBLE
        }else{
            View.VISIBLE
        }
    }
}