package com.altankoc.retrofitwithcoroutines.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.altankoc.retrofitwithcoroutines.R
import com.altankoc.retrofitwithcoroutines.databinding.ActivityDetayBinding
import com.altankoc.retrofitwithcoroutines.model.User

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

        binding.textID.text = gelenBilgi.id.toString()
        binding.textAdSoyad.text = gelenBilgi.name
        binding.textNickname.text = gelenBilgi.usarname
        binding.textAdres.text = "Sehir: ${gelenBilgi.address.city} Sokak: ${gelenBilgi.address.street}"



    }
}