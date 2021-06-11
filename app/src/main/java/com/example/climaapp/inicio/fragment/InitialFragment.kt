package com.example.climaapp.inicio.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.climaapp.R
import com.example.climaapp.databinding.FragmentInitialBinding
import com.example.climaapp.showweather.fragment.ShowWeatherFragment


class InitialFragment : Fragment() {

    private var _binding: FragmentInitialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInitialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickListeners(){
        binding.confirmarButton.setOnClickListener {
            val citySelected = checkCitySelected()
            findNavController().navigate(R.id.go_to_show_weather, bundleOf(
                ShowWeatherFragment.CITY_SELECTED to citySelected
            ))
        }
    }

    private fun checkCitySelected(): String {
        val radioButtonSelectedId: Int = binding.citiesRadioGroup.checkedRadioButtonId
        val radioButtonSelected: RadioButton = binding.root.findViewById(radioButtonSelectedId)
        return radioButtonSelected.text.toString()
    }

}