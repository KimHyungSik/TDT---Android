package com.todotracks.tdt.main_topic_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.fragment.app.Fragment
import com.todotracks.tdt.databinding.FragmentMainTopicListBinding

class MainTopicListScreen : Fragment() {

    private var _binding: FragmentMainTopicListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainTopicListBinding.inflate(inflater, container, false)
        val view = binding.root
        
        binding.composeView.setContent { 
            Text(text = "TDT")
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}