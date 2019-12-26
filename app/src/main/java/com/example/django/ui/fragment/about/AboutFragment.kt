package com.example.django.ui.fragment.about


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.django.R
import com.example.django.databinding.FragmentAboutBinding
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAboutBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_about, container, false
        )
        activity?.setTitle("About")

        binding.imageView.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse("https://www.themoviedb.org/documentation/api")
            activity!!.startActivity(i)
        }

        return binding.root
    }
}