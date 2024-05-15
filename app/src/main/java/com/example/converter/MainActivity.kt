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
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.converter.databinding.FragmentActivityMainBinding
import com.example.converter.databinding.GeneralActivityBinding
import com.example.converter.fragments.FragmentConvertToTjs
import com.example.converter.fragments.FragmentConverterActivity
import com.example.converter.fragments.FragmentExchanger
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
    private lateinit var navController: NavController

    private lateinit var fragmentConvertorActivity: FragmentConverterActivity
    private lateinit var fragmentExchanger: FragmentExchanger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = GeneralActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.exchanger_frag,
                R.id.converter_frag
            )
        )

        NavigationUI.setupWithNavController(binding.generalTextView, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.buttomMenu, navController)







        /*
        fragmentConvertorActivity = FragmentConverterActivity()
        fragmentExchanger = FragmentExchanger()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragmentConvertorActivity)
            .commit()

        binding.generalTextView.text = "Курсы НБТ"


        binding.buttomMenu.setOnNavigationItemSelectedListener { item ->
            binding.buttomMenu.isVisible = true
            when(item.itemId){

                R.id.item_1 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, fragmentConvertorActivity)
                        .commit()

                    binding.generalTextView.text = "Курсы НБТ"
                    true
                }

                R.id.item_2 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, fragmentExchanger)
                        .commit()

                    binding.generalTextView.text = "Обменники"
                    true
                }

                else -> false
            }
        }

         */

 



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