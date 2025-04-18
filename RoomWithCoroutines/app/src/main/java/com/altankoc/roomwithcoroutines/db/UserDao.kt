package com.altankoc.roomwithcoroutines.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.altankoc.roomwithcoroutines.model.User


@Dao
interface UserDao {

    @Query("Select * From users")
    suspend fun getAll(): List<User>

    @Insert
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

}