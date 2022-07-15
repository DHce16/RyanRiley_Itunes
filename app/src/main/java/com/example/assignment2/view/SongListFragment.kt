package com.example.assignment2.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.assignment2.R
import com.example.assignment2.databinding.FragmentSongListBinding
import com.example.assignment2.network.ApiService
import com.example.assignment2.data.SongResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongListFragment(genre: String) : Fragment() {

    lateinit var binding: FragmentSongListBinding
    var songGenre = genre

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongListBinding.inflate(layoutInflater)

        fetchSongs()

        return binding.root
    }

    private fun fetchSongs() {
        ApiService.getRetrofitInstance()?.create(ApiService::class.java)
            ?.getSong(songGenre)?.enqueue(object: Callback<SongResponse> {
                override fun onResponse(
                    call: Call<SongResponse>,
                    response: Response<SongResponse>
                ) {
                    if (response.isSuccessful){
                        val songAdapter = SongAdapter(response.body()!!.results, ::playSong)
                        binding.rvSongList.adapter = songAdapter
                    } else{
                        Toast.makeText(context,"The query was unsuccessful", Toast.LENGTH_LONG)
                    }
                }

                override fun onFailure(call: Call<SongResponse>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                }

                fun playSong(song: String){
                    val intent: Intent = Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(Uri.parse(song), "audio/mp4")
                    startActivity(intent)

                }
            })
    }
}