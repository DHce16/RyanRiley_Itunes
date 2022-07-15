package com.example.assignment2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment2.R
import com.example.assignment2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private val genreArray = arrayOf(
        "Rock",
        "Classic",
        "Pop"
    )

    //implement viewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.vpViewPager
        val tabLayout = binding.tlTabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = genreArray[position]
            when(position){
                0 -> tab.setIcon(R.drawable.rock_icon)
                1 -> tab.setIcon(R.drawable.classic_icon)
                2 -> tab.setIcon(R.drawable.pop_icon)
            }
        }.attach()
    }
}