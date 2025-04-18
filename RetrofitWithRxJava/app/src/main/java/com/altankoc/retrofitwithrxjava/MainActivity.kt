package com.altankoc.retrofitwithrxjava

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.altankoc.retrofitwithrxjava.adapter.UserAdapter
import com.altankoc.retrofitwithrxjava.databinding.ActivityMainBinding
import com.altankoc.retrofitwithrxjava.model.User
import com.altankoc.retrofitwithrxjava.service.RetrofitClient
import com.altankoc.retrofitwithrxjava.service.UserApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mDisposable = CompositeDisposable()
    private lateinit var users: ArrayList<User>

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

        mDisposable.add(
            RetrofitClient.apiService.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    gelenListe ->
                users = ArrayList(gelenListe)
                binding.recyclerView.adapter = UserAdapter(users)
            })
        )

    }


    private fun loadData(){



    }


    override fun onDestroy() {
        super.onDestroy()
        mDisposable.clear()
    }

}