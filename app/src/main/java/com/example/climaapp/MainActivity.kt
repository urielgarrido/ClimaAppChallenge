package com.example.climaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.example.climaapp.showweather.fragment.ShowWeatherFragment

class MainActivity : AppCompatActivity(), ShowWeatherFragment.ShowLoadingListener {
    private lateinit var layoutLoaders: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutLoaders = findViewById(R.id.layout_loaders)
    }

    override fun showLoading(show: Boolean) {
        layoutLoaders.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}