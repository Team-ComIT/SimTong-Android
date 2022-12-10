package com.comit.data.repository

import com.comit.data.datasource.LocalAuthDataSource
import com.comit.data.datasource.RemoteAuthDataSource
import com.comit.data.datasource.RemoteFileDataSource
import com.comit.data.util.FormDataUtil
import com.comit.domain.repository.AuthRepository
import com.comit.model.User
import java.io.File
import java.util.UUID
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteAuthDataSource: RemoteAuthDataSource,
    private val localAuthDataSource: LocalAuthDataSource,
    private val remoteFileDataSource: RemoteFileDataSource,
) : AuthRepository {

    override suspend fun signIn(
        employeeNumber: Int,
        password: String,
    ) {
        remoteAuthDataSource.signIn(
            employeeNumber = employeeNumber,
            password = password,
        ).also {
            localAuthDataSource.saveToken(it)
        }
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
        profileImage: File?
    ) {
        val profileImagePath = profileImage?.let {
            getImagePathByFile(
                file = profileImage,
            )
        }

        remoteAuthDataSource.signUp(
            name = name,
            employeeNumber = employeeNumber,
            email = email,
            password = password,
            nickname = nickname,
            profileImagePath = profileImagePath,
        ).also {
            localAuthDataSource.saveToken(it)
        }
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
        profileImg: File,
    ) {
        val profileImagePath = getImagePathByFile(
            file = profileImg,
        )

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

    private suspend fun getImagePathByFile(
        file: File,
    ): String {
        return remoteFileDataSource.uploadFile(
            file = FormDataUtil.getImageMultipart(
                key = "file",
                file = file,
            ),
        )
    }
}
