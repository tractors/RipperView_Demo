package com.will.ripperview_demo

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.bumptech.glide.request.transition.Transition
import com.will.ripperview_demo.adapter.MusicAdapter

import com.will.ripperview_demo.util.StatusBarUtil
import com.will.ripperview_demo.view.MyNestedScrollView
import com.will.ripperview_demo.view.UIUtils
import com.will.ripperview_demo.view.ViewCalculateUtil
import com.will.ripperview_demo.widget.BlurTransformation
import java.lang.reflect.Field
import kotlin.math.abs

/**
 * 仿网易云音乐播放列表
 */
class MusicActivity : AppCompatActivity() {
    companion object{
        //const val IMAGE_URL_MEDIUM : String = "http://p2.music.126.net/OlX-4S4L0Hdkyy_DQ27zag==/109951164459621658.jpg?param=400y400"
        const val IMAGE_URL_MEDIUM : String = "https://upload-images.jianshu.io/upload_images/1354448-49d5a95614966128.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"
    }
    private var toolbar : Toolbar? = null
    private var toolbarBg : ImageView? = null
    private var musicRecycler : RecyclerView? = null
    private var lvHeaderContail : LinearLayout? = null
    private var rvHeaderContainer : RelativeLayout? = null
    private var lvHeaderDetail : LinearLayout? = null
    private var headerMusicLog : ImageView? = null
    private var headerImageItem : ImageView? = null
    private var myNestedScrollView : MyNestedScrollView? = null
    private var headerBg : ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化UI布局适配工具类
        UIUtils.getInstance(this)
        setContentView(R.layout.activity_music)
        init()
        notifyData()
        httpPicture()
        initScrollViewListener()
    }

    var slidingDistance : Int = 0
    private fun initScrollViewListener() {
        //最好的测量就是不测量
        slidingDistance = UIUtils.getInstance().getHeight(490)

        myNestedScrollView!!.setOnMyScrollChangeListener(object : MyNestedScrollView.ScrollInterface{
            override fun onScrollChange(
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                scrollChangeHeader(scrollY)
            }

        })
    }

    private fun scrollChangeHeader(scrollY: Int) {
        var slidingScrollY = scrollY
        if (slidingScrollY < 0){
            slidingScrollY = 0
        }

        val alpha : Float = abs(slidingScrollY * 1.0f) /(slidingDistance)
        val drawable : Drawable = toolbarBg!!.drawable
        if (drawable != null){
            //设置为透明
            if (slidingScrollY <= slidingDistance){
                drawable.mutate().alpha = (alpha * 255).toInt()
                toolbarBg!!.setImageDrawable(drawable)
            } else {
                //设置为不透明
                drawable.mutate().alpha = 255
                toolbarBg!!.setImageDrawable(drawable)
            }
        }
    }

    private fun notifyData() {
        val layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        musicRecycler!!.layoutManager = layoutManager
        //需加，不然滑动不流畅
        musicRecycler!!.isNestedScrollingEnabled = false
        musicRecycler!!.setHasFixedSize(false)
        val adapter : MusicAdapter = MusicAdapter(this)
        adapter.notifyDataSetChanged()
        musicRecycler!!.adapter = adapter
    }

    private fun httpPicture() {
        Glide.with(this)
            .load(IMAGE_URL_MEDIUM)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .override(400,400)
            .into(headerImageItem!!)


       // val drawableCrossFadeFactory : DrawableCrossFadeFactory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        //"14"模糊度，"3"图片缩放3倍后再进行模糊
        Glide.with(this)
            .load(IMAGE_URL_MEDIUM)
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .transition(DrawableTransitionOptions().crossFade(500))//加载动画
            .apply (bitmapTransform(BlurTransformation(this,200,3)))
            .into(object : CustomTarget<Drawable>(){
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    lvHeaderContail!!.background = resource
                }

            })

        Glide.with(this)
            .load(IMAGE_URL_MEDIUM)
            .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
            .apply(bitmapTransform(BlurTransformation(this,200,3)))//设置高斯模糊
            .listener(object : RequestListener<Drawable>{ //监听加载状态
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    toolbar?.setBackgroundColor(Color.TRANSPARENT)
                    //toolbarBg?.background = resource
                    toolbarBg?.imageAlpha = 0
                    toolbarBg?.visibility = View.VISIBLE
                    return false
                }

            }).into(toolbarBg!!)
    }

    private fun init(){
        initView()
    }

    private fun initView() {
        toolbar = findViewById(R.id.toolbar)
        toolbarBg = findViewById(R.id.toolbar_bg)
        headerBg = findViewById(R.id.header_bg)
        musicRecycler = findViewById(R.id.music_recycler)
        myNestedScrollView = findViewById(R.id.nsv_scrollview)
        headerMusicLog = findViewById(R.id.header_music_log)
        lvHeaderDetail = findViewById(R.id.lv_header_detail)
        rvHeaderContainer = findViewById(R.id.rv_header_container)
        lvHeaderContail = findViewById(R.id.lv_header_contail)
        headerImageItem = findViewById(R.id.header_image_item)
        setSupportActionBar(toolbar)
        toolbar!!.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar!!.title = "歌单"
        toolbar!!.subtitle = "编辑推荐，编辑推荐歌单"
        toolbar!!.overflowIcon = ContextCompat.getDrawable(this,R.drawable.more)


//        val contentFrameLayout : ViewGroup = findViewById(android.R.id.content)
//        val parentView : View = contentFrameLayout.getChildAt(0)
//
//        if (parentView != null){
//            parentView.fitsSystemWindows = true
//        }

        //modifyToolBar()


        ViewCalculateUtil.setViewLinearLayoutParam(lvHeaderContail!!,1080,1014,0,0,0,0)
        ViewCalculateUtil.setViewLinearLayoutParam(rvHeaderContainer!!,1080,850,(164+UIUtils.getInstance().getSystemBarHeight(this)),0,0,0)
        ViewCalculateUtil.setViewLayoutParam(toolbar!!,1080,164,0,0,0,0)
        ViewCalculateUtil.setViewLayoutParam(toolbarBg!!,1080 , (164+UIUtils.getInstance().getSystemBarHeight(this)),0,0,0,0)
        ViewCalculateUtil.setViewLayoutParam(headerBg!!,1080,850,0,0,0,0)
        ViewCalculateUtil.setViewLayoutParam(lvHeaderDetail!!,1080,380,72,0,52,0)
        ViewCalculateUtil.setViewLinearLayoutParam(headerImageItem!!,380,380)
        ViewCalculateUtil.setViewLayoutParam(headerMusicLog!!,60,60,59,0,52,0)

        StatusBarUtil.setStatusBar(this,toolbar!!)
    }

    private fun modifyToolBar() {
        try {
            val imageButtonField: Field = toolbar!!.javaClass.getDeclaredField("mNavButtonView")
            imageButtonField.isAccessible = true
            val imageButton : ImageButton = imageButtonField.get(toolbar!!) as ImageButton
            val layoutParams : Toolbar.LayoutParams = imageButton.layoutParams as Toolbar.LayoutParams
            //适配代码
            layoutParams.topMargin = UIUtils.getInstance().getHeight(20)
            layoutParams.leftMargin = UIUtils.getInstance().getWidth(10)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_music,menu)
        return true
    }
}