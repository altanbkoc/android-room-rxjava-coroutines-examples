    package com.altankoc.roomwithcoroutines.view

    import android.content.Intent
    import android.os.Bundle
    import android.view.View
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import androidx.lifecycle.lifecycleScope
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.room.Room
    import com.altankoc.roomwithcoroutines.R
    import com.altankoc.roomwithcoroutines.adapter.UserAdapter
    import com.altankoc.roomwithcoroutines.databinding.ActivityMainBinding
    import com.altankoc.roomwithcoroutines.db.UserDao
    import com.altankoc.roomwithcoroutines.db.UserDatabase
    import com.altankoc.roomwithcoroutines.model.User
    import kotlinx.coroutines.CoroutineScope
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.cancel
    import kotlinx.coroutines.launch
    import kotlinx.coroutines.withContext

    class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
//        private lateinit var userDao: UserDao
//        private lateinit var db: UserDatabase
        private val db by lazy { UserDatabase.getDatabase(applicationContext) }
        private val userDao by lazy { db.userDao() }
        private lateinit var users: ArrayList<User>
        private val coroutineScope = CoroutineScope(Dispatchers.Main)



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
//
//            db = UserDatabase.getDatabase(applicationContext)
//            userDao = db.userDao()

            binding.recyclerView.layoutManager = LinearLayoutManager(this)

            users = arrayListOf()
            binding.recyclerView.adapter = UserAdapter(users)

            lifecycleScope.launch {
                try {
                    val userList = withContext(Dispatchers.IO) {
                        userDao.getAll()
                    }
                    users = ArrayList(userList)
                    binding.recyclerView.adapter = UserAdapter(users)
                } catch (e: Exception) {
                    e.printStackTrace()

                }
            }



        }



        fun gecisYap(view: View){
            val intent = Intent(this@MainActivity, EkleActivity::class.java)
            startActivity(intent)
        }


        override fun onDestroy() {
            super.onDestroy()
            coroutineScope.cancel()
        }
    }