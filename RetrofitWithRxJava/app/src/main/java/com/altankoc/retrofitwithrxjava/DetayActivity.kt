package com.altankoc.retrofitwithrxjava

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.altankoc.retrofitwithrxjava.databinding.ActivityDetayBinding
import com.altankoc.retrofitwithrxjava.model.User
import com.altankoc.retrofitwithrxjava.service.UserApi
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetayBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        val gelenBilgi = intent.getSerializableExtra("user") as User

        binding.textName.text = gelenBilgi.name
        binding.textUsename.text = gelenBilgi.username
        binding.textAdress.text = "Sehir: ${gelenBilgi.address.city} Sokak: ${gelenBilgi.address.street}"



    }
}