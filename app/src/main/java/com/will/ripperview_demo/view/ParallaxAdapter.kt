package com.will.ripperview_demo.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ParallaxAdapter: FragmentPagerAdapter {

    private var fragments : MutableList<ParallaxFragment> = ArrayList()

    constructor(fm: FragmentManager) : this(fm = fm,behavior = 0) {

    }

    constructor(fm: FragmentManager, behavior: Int,fragments: MutableList<ParallaxFragment>) : this(fm,behavior){
        this.fragments = fragments
    }

    constructor(fm: FragmentManager, behavior: Int) : super(fm, behavior){

    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun setFragments(fragments : MutableList<ParallaxFragment>){
        this.fragments = fragments
    }
}