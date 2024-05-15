package com.example.converter.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.converter.GetInformation
import com.example.converter.Item
import com.example.converter.R
import com.example.converter.databinding.FragmentActivityMainBinding
import com.example.converter.databinding.FragmentConverterActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentConvertToTjs: Fragment() {
    private var _binding: FragmentConverterActivityBinding? = null
    private val binding get() = _binding!!
    private val args: FragmentConvertToTjsArgs by navArgs()

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

        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.buttom_menu)
        bottomNavigationView.visibility = View.GONE

        /*
        val itemJson = arguments?.getString("itemJson")
        var item = Gson().fromJson(itemJson, Item::class.java)
         */

        val args = arguments
        val itemJson: String? = args?.getString("itemString")
        var item = Gson().fromJson(itemJson, Item::class.java)


        binding.valuteConverter.text = item.name
        binding.nameOfValuteConverter.text = item.fullName
        binding.valueOfValuteConverter.text = String.format("%.6f", item.value.toDouble() / item.nominal.toDouble()) + " TJS"

        Glide.with(binding.cardFlagConverter)
            .load(item.flag)
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.flagConverter)


        binding.editTxtInostran.hint = item.name
        binding.editTxtMestij.hint = "TJS"
        binding.kursValuts.text = "1 ${item.fullName} = ${binding.valueOfValuteConverter.text} Сомони"


        binding.editTxtInostran.doAfterTextChanged { txt ->
            if(binding.editTxtInostran.isFocused){
                val newTxt = txt?.replace(Regex(","),".")
                if(!txt.isNullOrBlank()) binding.editTxtMestij.text = Editable.Factory.getInstance().newEditable(String.format("%.6f", ((newTxt.toString()).toDouble()) * (item.value.toDouble() / item.nominal.toDouble())))
                else binding.editTxtMestij.text.clear()
            }
        }

        binding.editTxtMestij.doAfterTextChanged { txt ->
            if(binding.editTxtMestij.isFocused){
                val newTxt = txt?.replace(Regex(","),".")
                if(!newTxt.isNullOrBlank()) binding.editTxtInostran.text = Editable.Factory.getInstance().newEditable(String.format("%.6f", ((newTxt.toString()).toDouble()) / (item.value.toDouble() / item.nominal.toDouble())))
                else binding.editTxtInostran.text.clear()
            }
        }





    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}