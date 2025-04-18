package com.altankoc.roomwithrxjava.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.altankoc.roomwithrxjava.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable


@Dao
interface UserDao {

    @Query("Select * from users")
    fun getAll(): Flowable<List<User>>


    @Insert
    fun insert(user: User): Completable
}