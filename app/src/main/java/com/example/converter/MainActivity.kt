package com.example.converter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.converter.databinding.FragmentActivityMainBinding
import com.example.converter.databinding.GeneralActivityBinding
import com.example.converter.fragments.FragmentConvertToTjs
import com.example.converter.fragments.FragmentConverterActivity
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: GeneralActivityBinding

    private lateinit var fragmentConvertorActivity: FragmentConverterActivity
    private lateinit var fragmentConvertToTjs: FragmentConvertToTjs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = GeneralActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentConvertorActivity = FragmentConverterActivity()
        fragmentConvertToTjs = FragmentConvertToTjs()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragmentConvertorActivity)
            .commit()

        binding.generalTextView.text = "Курсы НБТ"

        /*
        binding.cours.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragmentConvertorActivity)
                .commit()

            binding.generalTextView.text = "Курсы НБТ"

        }

        binding.converter.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, fragmentConvertToTjs)
                .commit()

            binding.generalTextView.text = "Конвертор"

        }
         */
    }


}