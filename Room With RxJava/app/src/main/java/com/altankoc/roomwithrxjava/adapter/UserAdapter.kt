package com.altankoc.roomwithrxjava.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.altankoc.roomwithrxjava.databinding.RecyclerRowBinding
import com.altankoc.roomwithrxjava.model.User

class UserAdapter(val userList: List<User>): RecyclerView.Adapter<UserAdapter.UserHolder>() {

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

        holder.binding.recTextAd.text = userList[position].ad

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context,"Yas: ${userList[position].yas}",Toast.LENGTH_SHORT).show()
        }

    }
}