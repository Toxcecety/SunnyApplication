package com.example.sunnyapplication

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sunnyapplication.databinding.FragmentSunnyGalleryBinding
import com.example.sunnyapplication.databinding.FragmentSunnyPredictionsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SunnyPredictionsFragment : Fragment() {
    private lateinit var binding: FragmentSunnyPredictionsBinding
    private lateinit var soundPool: SoundPool
    private var soundId: Int = 0

    private val predictions = listOf(
        "Trust your nose.",
        "Stay pawsitive.",
        "Woof Woof Woof.",
        "A Sunny future...",
        "I don't know.",
        "Cloudy...cloudy.",
        "Oh wow... yikes.",
        "Interesting...",
        "Nope."
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSunnyPredictionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create a SoundPool instance
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        val scope = CoroutineScope(Dispatchers.Main)

        // Initialize the SoundPool after configuring audio attributes
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        // Load the sound effect into SoundPool
        soundId = soundPool.load(context, R.raw.magic_ring, 1)

        binding.navPredictionsToMenuButton.setOnClickListener {
            findNavController().navigate(R.id.action_sunnyPredictionsFragment_to_sunnyMenuFragment)
        }

        binding.buttonSunnyPredictionsTable.setOnClickListener {
            binding.buttonSunnyPredictionsTable.setBackgroundResource(R.drawable.pixel_sunny_fortune_heart)
            setEnabledButtons(false)
            scope.launch{
                delay(1000)
                withContext(Dispatchers.Main) {
                    binding.buttonSunnyPredictionsTable.setBackgroundResource(R.drawable.pixel_sunny_fortune)
                    setEnabledButtons(true)
                }
            }
        }

        binding.buttonCrystalBallButton.setOnClickListener {
            // Play sound
            soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
            setEnabledButtons(false)
            // Display the prediction
            val prediction = predictions[predictions.indices.random()]
            binding.smokeCloudPrediction.text = prediction
            binding.smokeCloudPrediction.visibility = View.VISIBLE
            scope.launch{
                delay(3000)
                withContext(Dispatchers.Main) {
                    binding.smokeCloudPrediction.visibility = View.GONE
                    setEnabledButtons(true)
                }
            }

        }
    }

    fun setEnabledButtons(boolean: Boolean) {
        binding.buttonSunnyPredictionsTable.isEnabled = boolean
        binding.buttonCrystalBallButton.isEnabled = boolean
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Release the SoundPool when done
        soundPool.release()
    }

}