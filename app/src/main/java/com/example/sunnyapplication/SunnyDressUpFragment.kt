package com.example.sunnyapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.sunnyapplication.databinding.FragmentSunnyAboutBinding
import com.example.sunnyapplication.databinding.FragmentSunnyDressUpBinding

class SunnyDressUpFragment : Fragment() {
    private lateinit var binding: FragmentSunnyDressUpBinding

    private val hats = listOf(
        R.drawable.nothing,
        R.drawable.hat_santa,
        R.drawable.hat_cowboy,
        R.drawable.hat_witch,
        R.drawable.hat_garbage,
        R.drawable.hat_propeller,
        R.drawable.hat_red_party,
        R.drawable.hat_butterfly_bow
    )

    private val collars = listOf(
        R.drawable.nothing,
        R.drawable.collar_bone,
        R.drawable.collar_pink_bow,
        R.drawable.collar_polka_bow,
        R.drawable.collar_lucky_bow,
        R.drawable.collar_delicate_bow
    )
    private var currentHatIndex = 0
    private var currentCollarIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSunnyDressUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navDressupToMenuButton.setOnClickListener {
            findNavController().navigate(R.id.action_sunnyDressUpFragment_to_sunnyMenuFragment)
        }

        updateHatImage()
        updateCollarImage()

        binding.arrowLeftHat.setOnClickListener {
            currentHatIndex = (currentHatIndex - 1 + hats.size) % hats.size
            updateHatImage()
        }
        binding.arrowRightHat.setOnClickListener{
            currentHatIndex = (currentHatIndex + 1) % hats.size
            updateHatImage()
        }

        binding.arrowLeftCollar.setOnClickListener {
            currentCollarIndex = (currentCollarIndex - 1 + collars.size) % collars.size
            updateCollarImage()
        }
        binding.arrowRightCollar.setOnClickListener{
            currentCollarIndex = (currentCollarIndex + 1) % collars.size
            updateCollarImage()
        }

    }

    private fun updateHatImage() {
        binding.hatOverlay.setImageResource(hats[currentHatIndex])
        binding.hatIndex.text = "$currentHatIndex"
    }

    private fun updateCollarImage() {
        binding.collarOverlay.setImageResource(collars[currentCollarIndex])
        binding.collarIndex.text = "$currentCollarIndex"
    }
}
