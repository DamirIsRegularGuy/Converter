package com.example.converter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.converter.databinding.ActivityMainBinding
import com.example.converter.databinding.ConverterActivityBinding
import com.google.gson.Gson

class ConverterActivity: AppCompatActivity() {

    private lateinit var  binding: ConverterActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ConverterActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemJson = intent.getStringExtra("item")
        val item = Gson().fromJson(itemJson, Item::class.java)
        binding.hoop.text = item.fullName
    }
}