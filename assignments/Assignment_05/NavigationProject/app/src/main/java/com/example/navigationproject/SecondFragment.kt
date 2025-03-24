package com.example.navigationproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.navigationproject.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSecondBinding.inflate(inflater, container, false)

        // Get the image name from the arguments
        val imageName = arguments?.getString("image_name")

        // Load the image based on the passed name
        when (imageName) {
            "android_image_1" -> binding.imageView.setImageResource(R.drawable.android_image_1)
            "android_image_2" -> binding.imageView.setImageResource(R.drawable.android_image_2)
            "android_image_3" -> binding.imageView.setImageResource(R.drawable.android_image_3)
        }

        return binding.root
    }
}

