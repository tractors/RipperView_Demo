package com.will.ripperview_demo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.bt_ripple).setOnClickListener {
            Intent(this,RippleActivity::class.java).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.bt_loading).setOnClickListener {
            Intent(this,PathMeasureAnimatorActivity::class.java).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.bt_music).setOnClickListener {

        }

        findViewById<Button>(R.id.bt_mark_fork).setOnClickListener {
            Intent(this,MarkAndForkActivity::class.java).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.bt_ui_relative_layout).setOnClickListener {
            Intent(this,UIRelativeLayoutActivity::class.java).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.bt_parallax).setOnClickListener {
            Intent(this,ParallaxActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}