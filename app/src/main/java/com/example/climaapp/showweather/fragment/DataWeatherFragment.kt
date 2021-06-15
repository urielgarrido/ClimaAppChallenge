package com.example.climaapp.showweather.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.climaapp.R
import com.example.climaapp.databinding.FragmentDataWeatherBinding
import com.example.climaapp.showweather.model.WeatherCityResponse
import java.text.SimpleDateFormat
import java.util.*

class DataWeatherFragment : Fragment() {

    private var _binding: FragmentDataWeatherBinding? = null
    private val binding get() = _binding!!

    private var weatherCityResponse: WeatherCityResponse? = null

    companion object {

        private const val WEATHER_CITY_RESPONSE = "weatherCityResponse"

        fun newInstance(weatherCityResponse: WeatherCityResponse): DataWeatherFragment {
            val args = bundleOf(
                WEATHER_CITY_RESPONSE to weatherCityResponse
            )
            val fragment = DataWeatherFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weatherCityResponse = it.getParcelable(WEATHER_CITY_RESPONSE) as? WeatherCityResponse
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_data_weather, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.weatherResponse = weatherCityResponse
        parseDate()
    }

    private fun parseDate() {
        if (weatherCityResponse?.dtText != null){
            val stringDateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val dateString = stringDateFormatter.parse(weatherCityResponse?.dtText!!)
            val dateFormat = SimpleDateFormat("EEEE, d MMMM HH a", Locale.getDefault())
            binding.date = dateFormat.format(dateString!!)
        } else {
            val dateFormat = SimpleDateFormat("EEEE, d MMMM HH a", Locale.getDefault())
            binding.date = dateFormat.format(Date())
        }
    }

}