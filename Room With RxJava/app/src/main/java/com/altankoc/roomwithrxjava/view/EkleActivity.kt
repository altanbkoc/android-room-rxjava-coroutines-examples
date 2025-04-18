package com.altankoc.roomwithrxjava.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.altankoc.roomwithrxjava.R
import com.altankoc.roomwithrxjava.databinding.ActivityEkleBinding
import com.altankoc.roomwithrxjava.db.UserDao
import com.altankoc.roomwithrxjava.db.UserDatabase
import com.altankoc.roomwithrxjava.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EkleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEkleBinding
    private lateinit var userDao: UserDao
    private lateinit var db: UserDatabase
    private var mDisposable = CompositeDisposable()


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

        val user = User(ad,yas.toInt())


        mDisposable.add(userDao.insert(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("Kişi başarıyla eklendi")
                handleResponse()
            },{
                error ->
                println("Hata meydana geldi: ${error.localizedMessage}")
            })
            )



    }

    fun handleResponse(){
        val intent = Intent(this@EkleActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        mDisposable.clear()
    }
}