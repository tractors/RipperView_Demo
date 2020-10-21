package com.will.ripperview_demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.will.ripperview_demo.R

class MusicAdapter(context:Context) : RecyclerView.Adapter<MusicAdapter.NormalTextViewHolder>() {

    private val mLayoutInflater : LayoutInflater
    private var mTitles : Array<String> = arrayOf<String>()

    class NormalTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val musicName : TextView
        val itemPosition : TextView

        init {
            musicName = itemView.findViewById(R.id.music_name)
            itemPosition = itemView.findViewById(R.id.item_position)
        }
    }

    init {
        mTitles = context.resources.getStringArray(R.array.music)
        mLayoutInflater = LayoutInflater.from(context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalTextViewHolder {
        return NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_list,parent,false))
    }

    override fun getItemCount(): Int {
        return if (mTitles == null) 0 else mTitles.size
    }

    override fun onBindViewHolder(holder: NormalTextViewHolder, position: Int) {
        holder.musicName.text = mTitles[position].toString()
        holder.itemPosition.text = position.toString()

    }
}