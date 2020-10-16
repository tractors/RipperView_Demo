package com.will.ripperview_demo.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.will.ripperview_demo.R
import com.will.ripperview_demo.bean.ParallaxViewTag
import java.lang.reflect.Constructor

/**
 * 平行控件的填充器
 */
class ParallaxLayoutInflater : LayoutInflater {

    private var fragment : ParallaxFragment? = null
    constructor(context: Context) : super(context) {}

    constructor(original: LayoutInflater?, newContext: Context?,fragment: ParallaxFragment?) : super(original, newContext){
        this.fragment = fragment
        factory2 = ParallaxFactory(this,fragment)
    }

    override fun cloneInContext(newContext: Context?): LayoutInflater {
        return ParallaxLayoutInflater(this,newContext,fragment)
    }

     class ParallaxFactory : Factory2 {
        private var inflater: LayoutInflater
        private var fragment : ParallaxFragment? = null
        private val sClassPrefix: Array<String> = arrayOf("android.widget.", "android.view.")
        //自定义的属性
        private val attrsIds : IntArray = intArrayOf(
            R.attr.a_in,
            R.attr.a_out,
            R.attr.x_in,
            R.attr.x_out,
            R.attr.y_in,
            R.attr.y_out
        )

        constructor(inflater: LayoutInflater,fragment: ParallaxFragment?){
            this.inflater = inflater
            this.fragment = fragment
        }
        @SuppressLint("ResourceType")
        override fun onCreateView(
            parent: View?,
            name: String,
            context: Context,
            attrs: AttributeSet
        ): View? {
            var view: View? = null
            //系统视图需要加上前缀
            if (name.contains(".")) {
                view = createMyView(name, context, attrs)
            } else {
                for (prefix in sClassPrefix) {
                    view = createMyView(prefix + name, context, attrs)
                    if (view != null){
                        break
                    }
                }
            }

            //将AttributeSet 取出自定义属性，封装成JavaBean 设置到ViewTag\
            val a :TypedArray = context.obtainStyledAttributes(attrs,attrsIds)
            if (a != null && a.length() > 0){
                //获取自定义属性到值
               val tag : ParallaxViewTag = ParallaxViewTag()
                tag.alphaIn = a.getFloat(0,0f)
                tag.alphaOut = a.getFloat(1,0f)
                tag.xIn = a.getFloat(2, 0f)
                tag.xOut = a.getFloat(3, 0f)
                tag.yIn = a.getFloat(4, 0f)
                tag.yOut = a.getFloat(5, 0f)
                //绑定标签和值
                view?.setTag(R.id.parallax_view_tag,tag)
            }

            a.recycle()
            if (view != null) {
                fragment?.getParallaxViews()?.add(view)
            }
            return view
        }

        private fun createMyView(string: String, context: Context, attrs: AttributeSet): View? {
            //反射
            try {

                val clazz = Class.forName(string)
                val constructor: Constructor<View> =
                    clazz.getConstructor(Context::class.java, AttributeSet::class.java) as Constructor<View>
                return constructor.newInstance(context, attrs)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null

        }

        override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
            return null
        }

    }
}