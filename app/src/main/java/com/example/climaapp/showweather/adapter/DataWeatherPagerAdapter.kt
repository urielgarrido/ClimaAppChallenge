package com.example.climaapp.showweather.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class DataWeatherPagerAdapter(
    private val fragments: List<Fragment>,
    supportFragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(supportFragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}