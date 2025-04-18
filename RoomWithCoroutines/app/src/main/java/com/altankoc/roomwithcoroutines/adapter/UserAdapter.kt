package com.altankoc.roomwithcoroutines.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.altankoc.roomwithcoroutines.R
import com.altankoc.roomwithcoroutines.databinding.RecyclerRowBinding
import com.altankoc.roomwithcoroutines.db.UserDao
import com.altankoc.roomwithcoroutines.db.UserDatabase
import com.altankoc.roomwithcoroutines.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserAdapter(val userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserHolder>() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private lateinit var db: UserDatabase
    private lateinit var userDao: UserDao
    private lateinit var context: Context


    class UserHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        context = parent.context
        db = UserDatabase.getDatabase(context)
        userDao = db.userDao()

        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val currentUser = userList[position]
        holder.binding.recTextAd.text = currentUser.ad

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "ID: ${currentUser.id} Yaş: ${currentUser.yas}", Toast.LENGTH_SHORT).show()
        }

        holder.binding.imageButton.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Silme İşlemi")
                .setMessage("${currentUser.ad} kişisini silmek istediğinize emin misiniz?")
                .setIcon(R.drawable.baseline_delete_24)
                .setCancelable(false)
                .setPositiveButton("Evet") { dialog, _ ->
                    deleteUser(currentUser, position)
                    dialog.dismiss()
                }
                .setNegativeButton("Hayır") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun deleteUser(user: User, position: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.delete(user)

            launch(Dispatchers.Main) {
                userList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, userList.size)
                Toast.makeText(context, "Kişi silindi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}