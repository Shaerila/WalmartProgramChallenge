package com.mindorks.retrofit.walmartprogramchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.retrofit.walmartprogramchallenge.R
import com.mindorks.retrofit.walmartprogramchallenge.databinding.ActivityMainBinding
import com.mindorks.retrofit.walmartprogramchallenge.helper.CountryAdapter
import com.mindorks.retrofit.walmartprogramchallenge.network.RetrofitObject
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpCookie

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launch{
            val response = try {
                RetrofitObject.api.getCountryInfo()
            } catch (e: IOException) {
                Log.d(TAG, "IOException")
                return@launch
            } catch (e: HttpException){
                Log.d(TAG, "HttpException, Response not what expected")
                return@launch
            }

            if(response.isSuccessful && response.body() != null){
                countryAdapter.countryInfoItem = response.body()!!
            } else {
                Log.d(TAG, "Response Not Found")
            }
        }

    }

    private fun setupRecyclerView() = binding.countryRecyclerview.apply {
        countryAdapter = CountryAdapter()
        adapter = countryAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)

    }
}