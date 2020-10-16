package com.will.ripperview_demo

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.will.ripperview_demo.view.ParallaxContainer

/**
 * 平行控件实例
 */
class ParallaxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parallax)
        val container: ParallaxContainer = findViewById(R.id.parallax_container)

        container.setUp(
            R.layout.view_intro_1,
            R.layout.view_intro_2,
            R.layout.view_intro_3,
            R.layout.view_intro_4,
            R.layout.view_intro_5,
            R.layout.view_login
        )

        val ivMan : ImageView = findViewById(R.id.iv_man)

        ivMan.setBackgroundResource(R.drawable.man_run)

        container.setIvMan(ivMan)
    }
}