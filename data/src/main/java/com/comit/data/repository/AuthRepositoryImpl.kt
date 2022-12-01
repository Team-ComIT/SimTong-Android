package com.comit.data.repository

import com.comit.data.datasource.RemoteAuthDataSource
import com.comit.domain.repository.AuthRepository
import com.comit.model.Token
import com.comit.model.User
import java.util.UUID
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteAuthDataSource: RemoteAuthDataSource,
) : AuthRepository {

    override suspend fun signIn(
        employeeNumber: Int,
        password: String,
    ): Token {
        return remoteAuthDataSource.signIn(
            employeeNumber = employeeNumber,
            password = password,
        )
    }

    override suspend fun verificationEmployee(
        name: String,
        employeeNumber: String,
    ) {
        return remoteAuthDataSource.verificationEmployee(
            name = name,
            employeeNumber = employeeNumber,
        )
    }

    override suspend fun signUp(
        name: String,
        employeeNumber: Int,
        email: String,
        password: String,
        nickname: String?,
        profileImagePath: String?
    ): Token {
        return remoteAuthDataSource.signUp(
            name = name,
            employeeNumber = employeeNumber,
            email = email,
            password = password,
            nickname = nickname,
            profileImagePath = profileImagePath,
        )
    }

    override suspend fun checkNicknameDuplication(
        nickname: String,
    ) {
        return remoteAuthDataSource.checkNicknameDuplication(
            nickname = nickname,
        )
    }

    override suspend fun changeSpot(
        spotId: UUID,
    ) {
        return remoteAuthDataSource.changeSpot(
            spotId = spotId,
        )
    }

    override suspend fun changeProfileImage(
        profileImagePath: String,
    ) {
        return remoteAuthDataSource.changeProfileImage(
            profileImagePath = profileImagePath,
        )
    }

    override suspend fun changeEmail(
        email: String,
    ) {
        return remoteAuthDataSource.changeEmail(
            email = email,
        )
    }

    override suspend fun changeNickname(
        nickname: String,
    ) {
        return remoteAuthDataSource.changeNickname(
            nickname = nickname,
        )
    }

    override suspend fun fetchUserInformation(): User {
        return remoteAuthDataSource.fetchUserInformation()
    }
}
