package com.example.climaapp.showweather.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.climaapp.R
import com.example.climaapp.databinding.FragmentShowWeatherBinding
import com.example.climaapp.showweather.repository.ShowWeatherRepositoryImpl
import com.example.climaapp.showweather.viewmodel.ShowWeatherViewModel
import com.example.climaapp.showweather.viewmodel.ShowWeatherViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ShowWeatherFragment : Fragment() {
    private var _binding: FragmentShowWeatherBinding? = null
    private val binding get() = _binding!!

    private val showWeatherViewModel: ShowWeatherViewModel by viewModels {
        ShowWeatherViewModelFactory(
            ShowWeatherRepositoryImpl()
        )
    }

    private var showListener: ShowLoadingListener? = null

    companion object {
        const val CITY_SELECTED = "city_selected"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_show_weather,container,false)
        arguments?.let {
            binding.citySelected = it.getString(CITY_SELECTED)
        }
        bindViewModel()
        getDataFromApi()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showListener = context as? ShowLoadingListener
    }

    override fun onDetach() {
        super.onDetach()
        showListener = null
    }

    private fun getDataFromApi() {
        val apiKey: String = resources.getString(R.string.apiKey)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val citySelected = binding.citySelected
                val weatherCity = showWeatherViewModel.getWeatherFromApi(citySelected!!, apiKey)
                binding.weatherResponse = weatherCity
            }catch (exception: Exception){
                requireActivity().runOnUiThread {
                    showInternetErrorDialog()
                }
            }
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

    private fun bindViewModel() {
        showWeatherViewModel.showLoading.observe(viewLifecycleOwner, {
            showListener?.showLoading(it)
        })
    }

    private fun setOnClickListeners() {
        binding.elegirOtraButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    interface ShowLoadingListener{
        fun showLoading(show: Boolean)
    }
}