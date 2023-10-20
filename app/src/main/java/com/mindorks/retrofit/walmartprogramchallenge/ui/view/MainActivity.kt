package com.mindorks.retrofit.walmartprogramchallenge.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.retrofit.walmartprogramchallenge.databinding.ActivityMainBinding
import com.mindorks.retrofit.walmartprogramchallenge.viewmodel.CountryAdapter
import com.mindorks.retrofit.walmartprogramchallenge.model.network.RetrofitObject
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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
                Toast.makeText(this@MainActivity,"IOException, No Internet or incorrect URL", Toast.LENGTH_LONG).show()
                Log.d(TAG, "IOException")
                return@launch
            } catch (e: HttpException){
                Toast.makeText(this@MainActivity,"HttpException, Response not what expected", Toast.LENGTH_LONG).show()
                Log.d(TAG, "HttpException, Response not what expected")
                return@launch
            }

            if(response.isSuccessful && response.body() != null){
                countryAdapter.countryInfoItem = response.body()!!
            } else {
                Toast.makeText(this@MainActivity,"Response Not Found", Toast.LENGTH_LONG).show()
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