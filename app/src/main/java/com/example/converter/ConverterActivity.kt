
package com.example.converter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.converter.databinding.FragmentConverterActivityBinding
import com.example.converter.fragments.FragmentConverterActivity
import com.google.gson.Gson

class ConverterActivity: AppCompatActivity() {

    private lateinit var  binding: FragmentConverterActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentConverterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemJson = intent.getStringExtra("item")
        val item = Gson().fromJson(itemJson, Item::class.java)

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

        /*
        binding.convertorTextView.setOnClickListener {
            onBackPressed()
        }


         */



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
        /**/










    }
}