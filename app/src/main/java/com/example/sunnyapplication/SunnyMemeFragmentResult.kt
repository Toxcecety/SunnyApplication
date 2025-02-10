package com.example.sunnyapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sunnyapplication.databinding.FragmentSunnyMemeBinding
import com.example.sunnyapplication.databinding.FragmentSunnyMemeResultBinding

class SunnyMemeFragmentResult : Fragment() {
    private lateinit var binding: FragmentSunnyMemeResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSunnyMemeResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val memeText = arguments?.getString("memeText")
        val sunnyPicture = arguments?.getInt("sunnyPicture")
        binding.sunnyTextResult.text = memeText
        sunnyPicture?.let { binding.sunnyImageResult.setImageResource(it) }

        binding.navMemeToMenuButton.setOnClickListener {
            findNavController().navigate(R.id.action_sunnyMemeFragmentResult_to_sunnyMenuFragment)
        }


    }
}