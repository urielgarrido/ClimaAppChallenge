package com.example.climaapp.inicio.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.climaapp.R
import com.example.climaapp.databinding.FragmentInitialBinding
import com.example.climaapp.showweather.ShowWeatherFragment


class InitialFragment : Fragment() {

    private var _binding: FragmentInitialBinding? = null
    private val binding get() = _binding
    private var citySelected = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInitialBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkRadioButton()
        clickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkRadioButton() {
        binding?.citiesRadioGroup?.setOnCheckedChangeListener { _, checkedId ->
            run {
                when (checkedId) {
                    binding?.city1RadioButton?.id -> citySelected =
                        binding?.city1RadioButton!!.text.toString()
                    binding?.city2RadioButton?.id -> citySelected =
                        binding?.city2RadioButton!!.text.toString()
                    binding?.city3RadioButton?.id -> citySelected =
                        binding?.city3RadioButton!!.text.toString()
                    binding?.city4RadioButton?.id -> citySelected =
                        binding?.city4RadioButton!!.text.toString()
                    binding?.city5RadioButton?.id -> citySelected =
                        binding?.city5RadioButton!!.text.toString()
                }
            }
        }
    }

    private fun clickListeners(){
        binding?.confirmarButton?.setOnClickListener {
            findNavController().navigate(R.id.go_to_show_weather, bundleOf(
                ShowWeatherFragment.CITY_SELECTED to citySelected
            ))
        }
    }

}