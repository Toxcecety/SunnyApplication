package com.example.sunnyapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sunnyapplication.databinding.FragmentSunnyMemeBinding
import com.example.sunnyapplication.databinding.FragmentSunnyMenuBinding

class SunnyMemeFragment : Fragment() {
    private lateinit var binding: FragmentSunnyMemeBinding

    private val sunnyPictures = listOf(
        R.drawable.picture_sunny_1,
        R.drawable.picture_sunny_2,
        R.drawable.picture_sunny_3
    )

    private var currentSunnyIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSunnyMemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navMemeToMenuButton.setOnClickListener {
            findNavController().navigate(R.id.action_sunnyMemeFragment_to_sunnyMenuFragment)
        }

        binding.arrowLeftImage.setOnClickListener{
            currentSunnyIndex = (currentSunnyIndex - 1 + sunnyPictures.size) % sunnyPictures.size
            updateMemePicture()
        }

        binding.arrowRightImage.setOnClickListener{
            currentSunnyIndex = (currentSunnyIndex + 1) % sunnyPictures.size
            updateMemePicture()
        }

        binding.submitMemeButton.setOnClickListener {
            val memeText = binding.memeInputText.text.toString()
            val sunnyPicture = sunnyPictures[currentSunnyIndex]
            val bundle = Bundle().apply {
                putString("memeText", memeText)
                putInt("sunnyPicture", sunnyPicture)
            }
            findNavController().navigate(R.id.action_sunnyMemeFragment_to_sunnyMemeFragmentResult, bundle)
        }
    }

    private fun updateMemePicture() {
        binding.sunnyImage.setImageResource(sunnyPictures[currentSunnyIndex])
        binding.imageIndex.text = "$currentSunnyIndex"
    }

}