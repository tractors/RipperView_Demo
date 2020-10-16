package com.will.ripperview_demo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.will.ripperview_demo.R;
import com.will.ripperview_demo.bean.ParallaxViewTag;

import java.lang.reflect.Constructor;

/**
 * 平行控件的填充器
 */
class ParallaxLayoutInflater2 extends android.view.LayoutInflater {
    private ParallaxFragment fragment;
    protected ParallaxLayoutInflater2(Context context) {
        super(context);
    }

    protected ParallaxLayoutInflater2(android.view.LayoutInflater original, Context newContext, ParallaxFragment fragment) {
        super(original, newContext);
        this.fragment = fragment;
        setFactory2(new ParallaxFactory(this));
    }


    @Override
    public android.view.LayoutInflater cloneInContext(Context newContext) {
        return new ParallaxLayoutInflater2(this,newContext,fragment);
    }

    class ParallaxFactory implements Factory2{

        private String[] sClassPrefix = {"android.widget.", "android.view."};
        private LayoutInflater inflater;
        //自定义的属性
        private int[] attrsIds = {
                R.attr.a_in,
                R.attr.a_out,
                R.attr.x_in,
                R.attr.x_out,
                R.attr.y_in,
                R.attr.y_out
        };
        public ParallaxFactory(LayoutInflater inflater) {
            this.inflater = inflater;
        }


        @SuppressLint("ResourceType")
        @Nullable
        @Override
        public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {

            View view = null;
            //系统视图需要加上前缀
            if (name.contains(".")){
                view = createMyView(name, context, attrs);
            } else {
                for (String prefix : sClassPrefix) {
                    view  = createMyView(prefix + name,context,attrs);
                    if (view != null){
                        break;
                    }
                }
            }

            //将AttributeSet 取出自定义属性，封装成JavaBean 设置到ViewTag\
            TypedArray a = context.obtainStyledAttributes(attrs,attrsIds);
            if (a != null && a.length() > 0) {
                //获取自定义属性到值
                ParallaxViewTag tag = new ParallaxViewTag();
                tag.setAlphaIn( a.getFloat(0,0f));
                tag.setAlphaOut(a.getFloat(1,0f));
                tag.setXIn(a.getFloat(2, 0f));
                tag.setXOut(a.getFloat(3, 0f));
                tag.setYIn(a.getFloat(4, 0f));
                tag.setYOut(a.getFloat(5, 0f));
                //绑定标签和值
                view.setTag(R.id.parallax_view_tag,tag);
            }
            a.recycle();
            if (view != null){
                fragment.getParallaxViews().add(view);
            }
            return view;
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        private View createMyView(String name, Context context, AttributeSet attrs) {
            try {
                Class clazz = Class.forName(name);
                Constructor<View> constructor = clazz.getConstructor(Context.class,AttributeSet.class);
                return constructor.newInstance(context,attrs);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
            return null;
        }
    }
}