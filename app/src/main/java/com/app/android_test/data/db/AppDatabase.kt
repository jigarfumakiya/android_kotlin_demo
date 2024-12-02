package com.app.android_test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.android_test.data.db.dao.UserDao


/**
 * @Author: Jigar Fumakiya
 * @Date: 02/12/24
 */

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao


}

 
