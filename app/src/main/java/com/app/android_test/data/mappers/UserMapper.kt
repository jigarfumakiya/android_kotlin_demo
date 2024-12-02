package com.app.android_test.data.mappers

import com.app.android_test.data.db.UserEntity
import com.app.android_test.domain.model.UserDomain

/**
 * @Author: Jigar Fumakiya
 * @Date: 02/12/24
 */

fun UserDomain.toDBEntity() = UserEntity(
    name = name
)

fun UserEntity.toDomain()= UserDomain(
    name = name
)