package com.example.assignment2.network

import com.example.assignment2.data.SongResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    @GET("api/")
//    fun getRandomSong(
//        @Query("results") results: Int =50
//
//    ): Call<SongResponse>

    @GET("search")
    fun getSong(
        @Query("term") term: String,
        @Query("amp;media") media: String = "music",
        @Query("amp;entity") entity: String = "song",
        @Query("amp;limit") limit: Int = 50
    ): Call<SongResponse>


    //    getDemUsers("gender")
    // static String value
    companion object {
        // ^ how we achieve static in kotlin
        private var retrofit: Retrofit? = null

        //Singleton
        fun getRetrofitInstance():Retrofit? {
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl("https://itunes.apple.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!
        }
    }
}