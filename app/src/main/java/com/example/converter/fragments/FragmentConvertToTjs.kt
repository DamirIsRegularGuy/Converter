package com.example.converter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.converter.GetInformation
import com.example.converter.Item
import com.example.converter.databinding.FragmentActivityMainBinding
import com.example.converter.databinding.FragmentConverterActivityBinding
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentConvertToTjs: Fragment() {
    private var _binding: FragmentConverterActivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConverterActivityBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.valuteConverter.text = "15623yui  "
        /*
        val itemJson = arguments?.getString("itemJson")
        val item = Gson().fromJson(itemJson, Item::class.java)

        binding.valuteConverter.text = item.name
        binding.nameOfValuteConverter.text = item.fullName
        binding.valueOfValuteConverter.text = String.format("%.6f", item.value.toDouble() / item.nominal.toDouble()) + " TJS"

        Glide.with(binding.cardFlagConverter)
            .load(item.flag)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.flagConverter)

         */

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}