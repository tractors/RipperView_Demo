package com.will.ripperview_demo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.will.ripperview_demo.view.ViewCalculateUtil
import org.w3c.dom.Text

/**
 * 不同手机屏幕适配实例
 */
class UIRelativeLayoutActivity : AppCompatActivity() {

    private  var text3 : TextView? = null
    private  var text4 : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_relativelayout_activity)
        text3 = findViewById(R.id.text3)
        text4 = findViewById(R.id.text4)

        ViewCalculateUtil.setViewLayoutParam(text3!!,504,100,0,0,0,0)
        ViewCalculateUtil.setViewLayoutParam(text4!!,1080,100,0,0,0,0)

        ViewCalculateUtil.setTextSize(text3!!,50)
    }
}