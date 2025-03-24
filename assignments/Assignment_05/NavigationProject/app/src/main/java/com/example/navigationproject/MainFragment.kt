package com.example.navigationproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.navigationproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        // Set OnClickListener for buttons to navigate to the SecondFragment
        binding.button1.setOnClickListener {
            navigateToSecondFragment("android_image_1")
        }

        binding.button2.setOnClickListener {
            navigateToSecondFragment("android_image_2")
        }

        binding.button3.setOnClickListener {
            navigateToSecondFragment("android_image_3")
        }

        return binding.root
    }

    private fun navigateToSecondFragment(imageName: String) {
        val bundle = Bundle()
        bundle.putString("image_name", imageName)

        // Create the second fragment instance and pass the bundle
        val secondFragment = SecondFragment()
        secondFragment.arguments = bundle

        // Perform the fragment transaction to navigate to the second fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, secondFragment)
            .commit()  // No addToBackStack() here, so no back navigation
    }
}
