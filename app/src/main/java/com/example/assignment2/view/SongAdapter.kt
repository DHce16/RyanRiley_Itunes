package com.example.assignment2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment2.R
import com.example.assignment2.data.RandomSong
import com.example.assignment2.databinding.SongListItemBinding

class SongAdapter(
    private val list: List<RandomSong>,
    private val playSong: (String) -> Unit
): RecyclerView.Adapter<SongAdapter.SongViewHolder>() {


    inner class SongViewHolder(private val binding: SongListItemBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun onBind(song: RandomSong){

            binding.tvSongTitle.text = song.trackName
            binding.tvArtistName.text = song.artistName
            binding.tvPrice.text = song.getPrice()

            Glide.with(binding.albumPhoto)
                .load(song.artworkUrl100)
                .placeholder(R.drawable.loading_icon)
                .into(binding.albumPhoto)

            binding.root.setOnClickListener {
                playSong(song.previewUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            SongListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}