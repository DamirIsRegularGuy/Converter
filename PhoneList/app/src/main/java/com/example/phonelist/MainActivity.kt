package com.example.phonelist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phonelist.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        Log.d("TAG_TEST", "!isSuccessful: что-то пошло не так")

        val phoneListService = retrofit.create(contacts::class.java)

        binding.root.layoutManager = LinearLayoutManager(this)

        phoneListService.getResults().enqueue(object : Callback<RutItem> {
            override fun onResponse(p0: Call<RutItem>, p1: Response<RutItem>) {
                if (p1.isSuccessful)
                {
                    val resultList = p1.body()?.results ?: emptyList()
                    Log.d("TAG_TEST", "onFailure: вообще что то пошло не так ${p1.body()!!.results}")
                    binding.root.adapter = PhoneListAdapter(resultList)

                }
                else
                {
                    Log.d("TAG_TEST", "!isSuccessful: что-то пошло не так")
                }
            }

            override fun onFailure(p0: Call<RutItem>, p1: Throwable) {
                Log.d("TAG_TEST", "onFailure: вообще что то пошло не так ${p1.message}")
            }
        })


    }



}






