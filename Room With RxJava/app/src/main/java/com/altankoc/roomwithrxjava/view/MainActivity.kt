package com.altankoc.roomwithrxjava.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.altankoc.roomwithrxjava.R
import com.altankoc.roomwithrxjava.adapter.UserAdapter
import com.altankoc.roomwithrxjava.databinding.ActivityMainBinding
import com.altankoc.roomwithrxjava.db.UserDao
import com.altankoc.roomwithrxjava.db.UserDatabase
import com.altankoc.roomwithrxjava.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private lateinit var userDao: UserDao
//    private lateinit var db: UserDatabase
    private val db by lazy { UserDatabase.getDatabase(applicationContext) }
    private val userDao by lazy { db.userDao() }
    private var mDisposable = CompositeDisposable()
    private lateinit var kisiler: ArrayList<User>
//    private lateinit var adapter: UserAdapter


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

        kisiler = arrayListOf()
        binding.recyclerView.adapter = UserAdapter(kisiler)

//        db = UserDatabase.getDatabase(applicationContext)
//        userDao = db.userDao()

        mDisposable.add(userDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleResponse(it)
            })
            )


    }

    fun handleResponse(gelenList:List<User>){
        kisiler.clear()
        kisiler = ArrayList(gelenList)
        binding.recyclerView.adapter = UserAdapter(kisiler)
    }



     fun gecisYap(view: View){
        val intent = Intent(this@MainActivity,EkleActivity::class.java)
        startActivity(intent)
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}