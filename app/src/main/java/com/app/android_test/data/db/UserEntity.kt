package com.app.android_test.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @Author: Jigar Fumakiya
 * @Date: 02/12/24
 */
@Entity(tableName = "user")
class UserEntity(
    @PrimaryKey
    val id: Int = 0,
    val name: String
)


 

 
