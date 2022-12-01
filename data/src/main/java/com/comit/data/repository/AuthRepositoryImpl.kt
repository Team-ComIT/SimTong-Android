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
    ) {
        // TODO ("local에 저장")
        remoteAuthDataSource.signIn(
            employeeNumber = employeeNumber,
            password = password,
        )
    }

    override suspend fun verificationEmployee(
        name: String,
        employeeNumber: String,
    ) {
        remoteAuthDataSource.verificationEmployee(
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
    ) {
        // TODO ("local에 저장")
        remoteAuthDataSource.signUp(
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
        remoteAuthDataSource.checkNicknameDuplication(
            nickname = nickname,
        )
    }

    override suspend fun changeSpot(
        spotId: UUID,
    ) {
        remoteAuthDataSource.changeSpot(
            spotId = spotId,
        )
    }

    override suspend fun changeProfileImage(
        profileImagePath: String,
    ) {
        remoteAuthDataSource.changeProfileImage(
            profileImagePath = profileImagePath,
        )
    }

    override suspend fun changeEmail(
        email: String,
    ) {
        remoteAuthDataSource.changeEmail(
            email = email,
        )
    }

    override suspend fun changeNickname(
        nickname: String,
    ) {
        remoteAuthDataSource.changeNickname(
            nickname = nickname,
        )
    }

    override suspend fun fetchUserInformation(): User {
        return remoteAuthDataSource.fetchUserInformation()
    }
}
