package com.altankoc.roomwithcoroutines.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.RoomDatabase
import com.altankoc.roomwithcoroutines.R
import com.altankoc.roomwithcoroutines.databinding.ActivityEkleBinding
import com.altankoc.roomwithcoroutines.databinding.ActivityMainBinding
import com.altankoc.roomwithcoroutines.db.UserDao
import com.altankoc.roomwithcoroutines.db.UserDatabase
import com.altankoc.roomwithcoroutines.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EkleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEkleBinding
    private lateinit var userDao: UserDao
    private lateinit var db: UserDatabase
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = UserDatabase.getDatabase(applicationContext)
        userDao = db.userDao()

    }



    fun ekle(view: View){

        val ad = binding.editTextAd.text.toString()
        val yas = binding.editTextYas.text.toString()

        val kisi = User(ad,yas.toInt())

        coroutineScope.launch {

                withContext(Dispatchers.IO){
                    userDao.insert(kisi)
                }
            val intent = Intent(this@EkleActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
//                withContext(Dispatchers.Main) {
//
//                }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

}