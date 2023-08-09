package com.chocolate.usecases.users

import com.chocolate.entities.user.OwnerUser
import com.chocolate.entities.user.User
import com.chocolate.entities.user.UserAttachments
import com.chocolate.entities.user.UserGroups
import com.chocolate.entities.user.UserSettings
import com.chocolate.entities.user.UserState
import com.chocolate.entities.user.Users
import repositories.UsersRepositories
import javax.inject.Inject

class UsersUSeCase @Inject constructor(private val usersRepositories: UsersRepositories) {


    suspend operator fun invoke()  {
     usersRepositories.getOwnUser()
    }
}