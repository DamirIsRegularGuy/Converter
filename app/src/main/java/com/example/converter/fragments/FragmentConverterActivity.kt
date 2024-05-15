package com.example.converter.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.converter.ConverterActivity
import com.example.converter.GetInformation
import com.example.converter.Item
import com.example.converter.R
import com.example.converter.ValutsListAdapter
import com.example.converter.databinding.FragmentActivityMainBinding
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.converter.databinding.FragmentConverterActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentConverterActivity: Fragment() {

    private var _binding: FragmentActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragmentConvertToTjs: FragmentConvertToTjs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityMainBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.buttom_menu)
        bottomNavigationView.visibility = View.VISIBLE



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

        fetchData(valutsList)

        fetchData(valutsList)

        binding.errorButton.setOnClickListener {
            fetchData(valutsList)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun fetchData(valutsList: GetInformation){

        binding.progressBar.visibility = View.VISIBLE
        binding.cardViewOfList.visibility = View.GONE
        binding.errorPanel.visibility = View.GONE

        val request = valutsList.getData().request()


        valutsList.getData().enqueue(object : Callback<List<Item>> {
            override fun onResponse(p0: Call<List<Item>>, p1: Response<List<Item>>) {
                if (this@FragmentConverterActivity.isAdded) {

                    if (p1.isSuccessful) {
                        fragmentConvertToTjs = FragmentConvertToTjs()

                        // binding.progressBar.visibility = View.GONE
                        val resultList = p1.body() ?: emptyList()
                        Log.d("TAG_TEST", "что то так ${resultList}")
                        binding.progressBar.visibility = View.GONE
                        binding.cardViewOfList.visibility = View.VISIBLE

                        binding.lisOfValute.adapter =
                            ValutsListAdapter(resultList, listener = { item, ->


                                val gson = Gson()
                                val itemJsonstr = gson.toJson(item)

                                val action = FragmentConverterActivityDirections.actionConverterFragToFragmentConvertToTjs(itemString = itemJsonstr)
                                findNavController().navigate(action)

                                /*
                        val bundle = Bundle().apply {
                            putString("itemJson", itemJson)
                        }

                        val fragment = FragmentConvertToTjs()
                        fragment.arguments = bundle


                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.container, fragment)
                            .addToBackStack(null)
                            .commit()
                         */


                                /*
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(R.id.container, FragmentConvertToTjs().apply {
                                arguments = bundle
                            })
                            .addToBackStack(null)
                            .commit()
                         */

                                /*
                        val intent = Intent(requireContext(), ConverterActivity::class.java)
                        val gson = Gson()
                        val itemJson = gson.toJson(item)
                        intent.putExtra("item", itemJson)
                        startActivity(intent)
                         */
                            })
                    } else {
                        //binding.progressBar.visibility = View.GONE
                        Log.d("TAG_TEST", "!isSuccessful: что то пошло не так")
                        Log.d("TAG_TEST", "onResponse: ${p1.body()}")
                        Log.d("TAG_TEST", "onResponse: ${p1.errorBody()?.string()}")
                        Log.d("TAG_TEST", "")
                        binding.progressBar.visibility = View.GONE
                        binding.cardViewOfList.visibility = View.GONE
                        binding.errorPanel.visibility = View.VISIBLE
                    }
                }
            }

            override fun onFailure(p0: Call<List<Item>>, p1: Throwable) {
                Log.d("TAG_TEST", "onFailure: вообще что то пошло не так ${p1.message}")
                binding.progressBar.visibility = View.GONE
                binding.cardViewOfList.visibility = View.GONE
                binding.errorPanel.visibility = View.VISIBLE
            }

        })
    }

}