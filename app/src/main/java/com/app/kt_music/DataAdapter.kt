package com.app.kt_music

import android.app.Activity
import android.media.Image
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DataAdapter(val context: Activity, val dataList: List<Data>): RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        // population the data into view
        val currentData = dataList[position]

        holder.title.text = currentData.title
        //loading te music image now
        Picasso.get().load(currentData.album.cover).into(holder.musicImage)

        // playing and pausing music
        val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())
        holder.play.setOnClickListener {
            mediaPlayer.start()
        }

        holder.pause.setOnClickListener {
            mediaPlayer.pause()
        }
    }



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //create the view in case the layout manager fails to create view for data

        val musicImage: ImageView
        val title: TextView
        val play: ImageButton
        val pause: ImageButton

        //initialising all of the above things
        init {
            musicImage = itemView.findViewById(R.id.musicImg)
            title = itemView.findViewById(R.id.musicTitle)
            play = itemView.findViewById(R.id.playBtn)
            pause = itemView.findViewById(R.id.pauseBtn)
        }
    }
}
