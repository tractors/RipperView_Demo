package com.will.ripperview_demo.view

import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

/**
 *布局屏幕适配计算工具类
 */
class ViewCalculateUtil {

    companion object {

        /**
         * 根据屏幕的大小设置view的高度，间距
         */
        fun setViewLayoutParam(
            view: View,
            width: Int,
            height: Int,
            topMargin: Int,
            bottomMargin: Int,
            leftMargin: Int,
            rightMargin: Int
        ) {
            val layoutParams: RelativeLayout.LayoutParams =
                view.layoutParams as RelativeLayout.LayoutParams
            if (layoutParams != null) {
                if (width != RelativeLayout.LayoutParams.MATCH_PARENT && width != RelativeLayout.LayoutParams.WRAP_CONTENT && width != RelativeLayout.LayoutParams.FILL_PARENT) {
                    layoutParams.width = UIUtils.getInstance().getWidth(width)
                } else {
                    layoutParams.width = width
                }

                if (height != RelativeLayout.LayoutParams.MATCH_PARENT && height != RelativeLayout.LayoutParams.WRAP_CONTENT && height != RelativeLayout.LayoutParams.FILL_PARENT) {
                    layoutParams.height = UIUtils.getInstance().getHeight(height)
                } else {
                    layoutParams.height = height
                }

                layoutParams.topMargin = UIUtils.getInstance().getHeight(topMargin)
                layoutParams.bottomMargin = UIUtils.getInstance().getHeight(bottomMargin)
                layoutParams.leftMargin = UIUtils.getInstance().getWidth(leftMargin)
                layoutParams.rightMargin = UIUtils.getInstance().getWidth(rightMargin)
                view.layoutParams = layoutParams
            } else {

            }
        }

        /**
         * FrameLayout 布局屏幕适配
         */
        fun setViewFrameLayoutParam(
            view: View,
            width: Int,
            height: Int,
            topMargin: Int,
            bottomMargin: Int,
            leftMargin: Int,
            rightMargin: Int
        ) {
            val layoutParams: FrameLayout.LayoutParams =
                view.layoutParams as FrameLayout.LayoutParams

            if (width != FrameLayout.LayoutParams.MATCH_PARENT && width != FrameLayout.LayoutParams.WRAP_CONTENT && width != FrameLayout.LayoutParams.FILL_PARENT) {
                layoutParams.width = UIUtils.getInstance().getWidth(width)
            } else {
                layoutParams.width = width
            }

            if (height != FrameLayout.LayoutParams.MATCH_PARENT && height != FrameLayout.LayoutParams.WRAP_CONTENT && height != FrameLayout.LayoutParams.FILL_PARENT) {
                layoutParams.height = UIUtils.getInstance().getHeight(height)
            } else {
                layoutParams.height = height
            }

            layoutParams.topMargin = UIUtils.getInstance().getHeight(topMargin)
            layoutParams.bottomMargin = UIUtils.getInstance().getHeight(bottomMargin)
            layoutParams.leftMargin = UIUtils.getInstance().getWidth(leftMargin)
            layoutParams.rightMargin = UIUtils.getInstance().getWidth(rightMargin)
            view.layoutParams = layoutParams
        }

        /**
         * 设置view的内边距
         */
        fun setViewPadding(
            view: View,
            topPadding: Int,
            bottomPadding: Int,
            leftPadding: Int,
            rightPadding: Int
        ) {
            view.setPadding(
                UIUtils.getInstance().getWidth(leftPadding),
                UIUtils.getInstance().getHeight(topPadding),
                UIUtils.getInstance().getWidth(rightPadding),
                UIUtils.getInstance().getHeight(bottomPadding)
            )
        }

        /**
         * 设置字号
         */
        fun setTextSize(view: TextView, size: Int) {
            view.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                UIUtils.getInstance().getHeight(size).toFloat()
            )
        }

        /**
         * 设置LinearLayout中 view的高度宽度
         */
        fun setViewLinearLayoutParam(view: View,width: Int,height: Int){
            val layoutParams : LinearLayout.LayoutParams = view.layoutParams as LinearLayout.LayoutParams
            if (width != LinearLayout.LayoutParams.MATCH_PARENT && width != LinearLayout.LayoutParams.WRAP_CONTENT && width != LinearLayout.LayoutParams.FILL_PARENT){
                layoutParams.width = UIUtils.getInstance().getWidth(width)
            } else {
                layoutParams.width = width
            }

            if (height != LinearLayout.LayoutParams.MATCH_PARENT && height != LinearLayout.LayoutParams.WRAP_CONTENT && height != LinearLayout.LayoutParams.FILL_PARENT){
                layoutParams.height = UIUtils.getInstance().getWidth(height)
            } else {
                layoutParams.height = height
            }

            view.layoutParams = layoutParams
        }

        /**
         * 设置ViewGroup的高宽度
         */
        fun setViewGroupLayoutParam(view: View,width: Int,height: Int){
            val layoutParams : ViewGroup.LayoutParams = view.layoutParams

            if (width != ViewGroup.LayoutParams.MATCH_PARENT && width != ViewGroup.LayoutParams.WRAP_CONTENT && width != ViewGroup.LayoutParams.FILL_PARENT){
                layoutParams.width = UIUtils.getInstance().getWidth(width)
            } else {
                layoutParams.width = width
            }

            if (height != ViewGroup.LayoutParams.MATCH_PARENT && height != ViewGroup.LayoutParams.WRAP_CONTENT && height != ViewGroup.LayoutParams.FILL_PARENT){
                layoutParams.height = UIUtils.getInstance().getWidth(height)
            } else {
                layoutParams.height = height
            }

            view.layoutParams = layoutParams
        }

        /**
         * 设置LinearLayout中 view的高度宽度
         */
        fun setViewLinearLayoutParam(view: View,width: Int,height: Int,topMargin: Int,bottomMargin: Int,leftMargin: Int,rightMargin: Int){
            val layoutParams : LinearLayout.LayoutParams = view.layoutParams as LinearLayout.LayoutParams
            if (width != LinearLayout.LayoutParams.MATCH_PARENT && width != LinearLayout.LayoutParams.WRAP_CONTENT && width != LinearLayout.LayoutParams.FILL_PARENT){
                layoutParams.width = UIUtils.getInstance().getWidth(width)
            } else {
                layoutParams.width = width
            }

            if (height != LinearLayout.LayoutParams.MATCH_PARENT && height != LinearLayout.LayoutParams.WRAP_CONTENT && height != LinearLayout.LayoutParams.FILL_PARENT){
                layoutParams.height = UIUtils.getInstance().getWidth(height)
            } else {
                layoutParams.height = height
            }

            layoutParams.topMargin = UIUtils.getInstance().getHeight(topMargin)
            layoutParams.bottomMargin = UIUtils.getInstance().getHeight(bottomMargin)
            layoutParams.leftMargin = UIUtils.getInstance().getWidth(leftMargin)
            layoutParams.rightMargin = UIUtils.getInstance().getWidth(rightMargin)
            view.layoutParams = layoutParams
        }
    }
}