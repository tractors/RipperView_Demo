package com.will.ripperview_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.will.ripperview_demo.view.Density

/**
 * 按屏幕密度适配屏幕
 */
class DensityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Density.setDensity(application,this)
        setContentView(R.layout.activity_density)
    }
}