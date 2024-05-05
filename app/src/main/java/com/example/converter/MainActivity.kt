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
import com.example.converter.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val logging =  HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    val client =  OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://appsmile.ru/api/academy/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val valutsList = retrofit.create(GetInformation::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData(valutsList)

        binding.errorButton.setOnClickListener {
            fetchData(valutsList)
        }



    }

    fun fetchData(valutsList: GetInformation){

        //binding.progressBar.visibility = View.VISIBLE
        binding.errorTextView.visibility = View.GONE
        binding.errorButton.visibility = View.GONE

        val request = valutsList.getData().request()


        valutsList.getData().enqueue(object : Callback<List<Item>>{
            override fun onResponse(p0: Call<List<Item>>, p1: Response<List<Item>>) {
                if(p1.isSuccessful){

                   // binding.progressBar.visibility = View.GONE
                    val resultList = p1.body()?: emptyList()
                    Log.d("TAG_TEST", "что то так ${resultList}")
                    binding.lisOfValute.visibility = View.VISIBLE
                    binding.lisOfValute.adapter = ValutsListAdapter(resultList, listener = { item,   ->
                        val intent = Intent(this@MainActivity, ConverterActivity::class.java)
                        val gson = Gson()
                        val itemJson = gson.toJson(item)
                        intent.putExtra("item", itemJson)
                        startActivity(intent)
                    })
                }
                else{
                    //binding.progressBar.visibility = View.GONE
                    Log.d("TAG_TEST", "!isSuccessful: что то пошло не так")
                    Log.d("TAG_TEST", "onResponse: ${p1.body()}")
                    Log.d("TAG_TEST", "onResponse: ${p1.errorBody()?.string()}")
                    Log.d("TAG_TEST", "")
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.errorButton.visibility = View.VISIBLE
                }
            }

            override fun onFailure(p0: Call<List<Item>>, p1: Throwable) {
                Log.d("TAG_TEST", "onFailure: вообще что то пошло не так ${p1.message}")
                //binding.progressBar.visibility = View.GONE
                binding.errorTextView.visibility = View.VISIBLE
                binding.errorButton.visibility = View.VISIBLE
            }

        })
    }
}