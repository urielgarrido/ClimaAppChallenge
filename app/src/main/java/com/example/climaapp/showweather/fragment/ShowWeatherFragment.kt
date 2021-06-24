package com.example.climaapp.showweather.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.climaapp.R
import com.example.climaapp.databinding.FragmentShowWeatherBinding
import com.example.climaapp.showweather.adapter.DataWeatherPagerAdapter
import com.example.climaapp.showweather.model.WeatherCityResponse
import com.example.climaapp.showweather.repository.ShowWeatherRepositoryImpl
import com.example.climaapp.showweather.viewmodel.ShowWeatherViewModel
import com.example.climaapp.showweather.viewmodel.ShowWeatherViewModelFactory
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch

class ShowWeatherFragment : Fragment() {
    private var _binding: FragmentShowWeatherBinding? = null
    private val binding get() = _binding!!

    private val weatherListFiltered: MutableList<WeatherCityResponse> =
        emptyList<WeatherCityResponse>().toMutableList()

    private val showWeatherViewModel: ShowWeatherViewModel by viewModels {
        ShowWeatherViewModelFactory(
            ShowWeatherRepositoryImpl()
        )
    }

    companion object {
        const val CITY_SELECTED = "city_selected"
    }

    interface ShowLoadingListener {
        fun showLoading(show: Boolean)
    }

    private var showListener: ShowLoadingListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showListener = context as? ShowLoadingListener
    }

    override fun onDetach() {
        super.onDetach()
        showListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_show_weather, container, false)
        arguments?.let {
            binding.citySelected = it.getString(CITY_SELECTED)
        }
        bindViewModel()
        getDataFromApi()
        return binding.root
    }

    private fun bindViewModel() {
        showWeatherViewModel.showLoading.observe(viewLifecycleOwner, {
            showListener?.showLoading(it)
        })

        showWeatherViewModel.weatherDataForToday.observe(viewLifecycleOwner, {
            it?.let {
                weatherListFiltered.add(0, it)
            }
        })

        showWeatherViewModel.weatherDataForFiveDays.observe(viewLifecycleOwner, {
            it?.let {
                weatherListFiltered.addAll(it.list.filter { weatherCityResponse ->
                    weatherCityResponse.dtText?.contains(showWeatherViewModel.getActualHours())
                        ?: false
                })
            }.also {
                setupAdapterToViewPager()
            }
        })
    }

    private fun getDataFromApi() {
        val apiKey: String = resources.getString(R.string.apiKey)
        try {
            val citySelected = binding.citySelected
            showWeatherViewModel.getDataWeather(citySelected!!, apiKey)
            binding.screenConstraintLayout.visibility = View.VISIBLE
        } catch (exception: Exception) {
            binding.screenConstraintLayout.visibility = View.VISIBLE
            showInternetErrorDialog()
        }
    }

    private fun setupAdapterToViewPager() {
        val adapter = DataWeatherPagerAdapter(
            createDataWeatherFragments(),
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.dataWeatherViewPager.adapter = adapter
    }

    private fun createDataWeatherFragments(): List<DataWeatherFragment> {
        return weatherListFiltered.map {
            DataWeatherFragment.newInstance(it)
        }
    }

    private fun showInternetErrorDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.error))
            .setMessage(getString(R.string.no_se_logro_obtener_datos))
            .setPositiveButton(
                getString(R.string.aceptar)
            ) { dialog, _ ->
                dialog.dismiss()
                findNavController().popBackStack()
            }
            .show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.elegirOtraButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}