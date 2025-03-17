package com.example.navigationproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.navigationproject.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Button click listeners to pass image resources to SecondFragment
        binding.buttonImage1.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSecondFragment(R.drawable.android_image_1)
            Navigation.findNavController(it).navigate(action)
        }
        binding.buttonImage2.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSecondFragment(R.drawable.android_image_2)
            Navigation.findNavController(it).navigate(action)
        }
        binding.buttonImage3.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSecondFragment(R.drawable.android_image_3)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
