package com.will.ripperview_demo

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * 仿网易云音乐播放列表
 */
class MusicActivity : AppCompatActivity() {

    private var toolbar : Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        init()
    }

    private fun init(){
        initView()
    }

    private fun initView() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar!!.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar?.title = "歌单"
        toolbar?.subtitle = "编辑推荐，编辑推荐歌单"
    }
}