package com.altankoc.retrofitwithrxjava.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.altankoc.retrofitwithrxjava.DetayActivity
import com.altankoc.retrofitwithrxjava.databinding.RecyclerRowBinding
import com.altankoc.retrofitwithrxjava.model.User

class UserAdapter(val userList: ArrayList<User>):RecyclerView.Adapter<UserAdapter.UserHolder>() {


    class UserHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return UserHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.binding.recTextUsername.text = userList[position].username

        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context,DetayActivity::class.java)
            intent.putExtra("user",userList[position])
            holder.itemView.context.startActivity(intent)
        }
    }
}