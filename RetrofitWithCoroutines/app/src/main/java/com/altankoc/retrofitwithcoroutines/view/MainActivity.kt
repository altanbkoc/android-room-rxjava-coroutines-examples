package com.altankoc.retrofitwithcoroutines.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.altankoc.retrofitwithcoroutines.R
import com.altankoc.retrofitwithcoroutines.adapter.UserAdapter
import com.altankoc.retrofitwithcoroutines.databinding.ActivityMainBinding
import com.altankoc.retrofitwithcoroutines.model.User
import com.altankoc.retrofitwithcoroutines.service.RetrofitClient
import com.altankoc.retrofitwithcoroutines.service.UserApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var users: ArrayList<User>
    private val coroutinesScope = CoroutineScope(Dispatchers.Main)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        users = arrayListOf()
        binding.recyclerView.adapter = UserAdapter(users)


        lifecycleScope.launch {
            val gelenKisiler = withContext(Dispatchers.IO){
                RetrofitClient.apiService.getData()
            }

                if(gelenKisiler.isSuccessful){
                    gelenKisiler.body()?.let {
                        users = ArrayList(it)
                        users?.let {
                            val userAdapter = UserAdapter(it)
                            binding.recyclerView.adapter = userAdapter
                        }
                    }
                }


        }


    }


    override fun onDestroy() {
        super.onDestroy()
        coroutinesScope.cancel()
    }
}