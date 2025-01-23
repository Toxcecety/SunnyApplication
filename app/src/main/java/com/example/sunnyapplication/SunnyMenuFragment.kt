package com.example.sunnyapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sunnyapplication.databinding.FragmentSunnyMenuBinding

class SunnyMenuFragment : Fragment() {
    private lateinit var binding: FragmentSunnyMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSunnyMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navMenuToAboutButton.setOnClickListener {
            findNavController().navigate(R.id.action_sunnyMenuFragment_to_sunnyAboutFragment)
        }
        binding.navMenuToGalleryButton.setOnClickListener {
            findNavController().navigate(R.id.action_sunnyMenuFragment_to_sunnyGalleryFragment)
        }
        binding.navMenuToDressupButton.setOnClickListener {
            findNavController().navigate(R.id.action_sunnyMenuFragment_to_sunnyDressUpFragment)
        }
        binding.navMenuToPredictionsButton.setOnClickListener {
            findNavController().navigate(R.id.action_sunnyMenuFragment_to_sunnyPredictionsFragment)
        }


    }
}