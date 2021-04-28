package com.example.myapplication.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.example.myapplication.R
import com.example.myapplication.databinding.DetailFragmentBinding

class DetailFragment : Fragment(R.layout.detail_fragment) {

    private lateinit var binding : DetailFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailFragmentBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val content = DetailFragmentArgs.fromBundle(it).itemContent
            binding.tvTile.text = "Title : ${content.mediaTitleCustom}"
            binding.tvDate.text = "Date : ${content.mediaDate?.dateString}"
            binding.tvMedia.text = "Media URl  : ${content.mediaUrl}"
        }
    }

}