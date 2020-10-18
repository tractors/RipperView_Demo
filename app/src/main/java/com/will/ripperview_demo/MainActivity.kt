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
            Intent(this,MusicActivity::class.java).also {
                startActivity(it)
            }
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

        findViewById<Button>(R.id.bt_screen).setOnClickListener {
            Intent(this,ScreenLayoutActivity::class.java).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.bt_percent).setOnClickListener {
            Intent(this,PercentActivity::class.java).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.bt_customize_percent).setOnClickListener {
            Intent(this,CustomizePercentActivity::class.java).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.bt_density).setOnClickListener {
            Intent(this,DensityActivity::class.java).also {
                startActivity(it)
            }
        }

        findViewById<Button>(R.id.bt_display_cutout).setOnClickListener {
            Intent(this,DisplayCutoutActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}