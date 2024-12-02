package com.app.android_test.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.android_test.data.db.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Jigar Fumakiya
 * @Date: 02/12/24
 */


@Dao
interface UserDao {
    @Query("SELECT COUNT(*) > 0 FROM user")
    suspend fun userLogged(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): Flow<List<UserEntity>>

    @Query("DELETE FROM user")
    suspend fun clearUser()


}

 
