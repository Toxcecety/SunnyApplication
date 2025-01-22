package com.example.sunnyapplication

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sunnyapplication.databinding.FragmentSunnyGalleryBinding
import com.example.sunnyapplication.databinding.FragmentSunnyMenuBinding

class SunnyGalleryFragment : Fragment() {
    private lateinit var binding: FragmentSunnyGalleryBinding
    private lateinit var soundPool: SoundPool
    private var soundId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSunnyGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var score = 0

        // Create a SoundPool instance
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        // Initialize the SoundPool after configuring audio attributes
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        // Load the sound effect into SoundPool
        soundId = soundPool.load(context, R.raw.gaming_lock, 1)

        binding.navGalleryToMenuButton.setOnClickListener {
            findNavController().navigate(R.id.action_sunnyGalleryFragment_to_sunnyMenuFragment)
        }
        binding.buttonFrame1.setOnClickListener {
            animate(binding.buttonFrame1)
        }

        binding.buttonFrame2.setOnClickListener {
            animate(binding.buttonFrame2)
        }

        binding.buttonFrame3.setOnClickListener {
            animate(binding.buttonFrame3)
        }

        binding.buttonFrame4.setOnClickListener {
            animate(binding.buttonFrame4)
        }

        binding.buttonFrame5.setOnClickListener {
            animate(binding.buttonFrame5)
            Toast.makeText(context, "Looking good Sunny!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun animate(T: View) {
        // Play sound
        soundPool.play(soundId, 1f, 1f, 0, 0, 1f)

        // Scale animation
        val scaleX = ObjectAnimator.ofFloat(T, "scaleX", 1f, 1.2f, 1f)
        val scaleY = ObjectAnimator.ofFloat(T, "scaleY", 1f, 1.2f, 1f)

        // Set the duration and start the animation
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY)
        animatorSet.duration = 300
        animatorSet.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Release the SoundPool when done
        soundPool.release()
    }
}
