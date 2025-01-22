package com.example.sunnyapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sunnyapplication.databinding.FragmentSunnyAboutBinding
import com.example.sunnyapplication.databinding.FragmentSunnyMenuBinding


class SunnyAboutFragment : Fragment() {
    private lateinit var binding: FragmentSunnyAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSunnyAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navAboutToMenuButton.setOnClickListener {
            findNavController().navigate(R.id.action_sunnyAboutFragment_to_sunnyMenuFragment)
        }
    }
}