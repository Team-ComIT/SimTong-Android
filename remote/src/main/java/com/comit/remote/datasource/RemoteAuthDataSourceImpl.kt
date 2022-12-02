package com.comit.remote.datasource

import com.comit.data.datasource.RemoteAuthDataSource
import com.comit.model.Token
import com.comit.model.User
import com.comit.remote.api.AuthAPI
import com.comit.remote.mapper.toModel
import com.comit.remote.request.users.ChangeEmailRequest
import com.comit.remote.request.users.ChangeNicknameRequest
import com.comit.remote.request.users.ChangeProfileImageRequest
import com.comit.remote.request.users.ChangeSpotRequest
import com.comit.remote.request.users.SignInRequest
import com.comit.remote.request.users.SignUpRequest
import java.util.UUID
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI,
) : RemoteAuthDataSource {

    override suspend fun signIn(
        employeeNumber: Int,
        password: String
    ): Token {
        return authAPI.signIn(
            request = SignInRequest(
                employeeNumber = employeeNumber,
                password = password,
            )
        ).toModel()
    }

    override suspend fun verificationEmployee(
        name: String,
        employeeNumber: String,
    ) {
        authAPI.verificationEmployee(
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
        return authAPI.signUp(
            request = SignUpRequest(
                name = name,
                employeeNumber = employeeNumber,
                email = email,
                password = password,
                nickname = nickname,
                profileImagePath = profileImagePath,
            )
        ).toModel()
    }

    override suspend fun checkNicknameDuplication(
        nickname: String,
    ) {
        authAPI.checkNicknameDuplication(
            nickname = nickname,
        )
    }

    override suspend fun changeSpot(
        spotId: UUID,
    ) {
        authAPI.changeSpot(
            request = ChangeSpotRequest(
                spotId = spotId,
            )
        )
    }

    override suspend fun changeProfileImage(
        profileImagePath: String,
    ) {
        authAPI.changeProfileImage(
            request = ChangeProfileImageRequest(
                profileImagePath = profileImagePath,
            )
        )
    }

    override suspend fun changeEmail(
        email: String,
    ) {
        authAPI.changeEmail(
            request = ChangeEmailRequest(
                email = email,
            )
        )
    }

    override suspend fun changeNickname(
        nickname: String,
    ) {
        authAPI.changeNickname(
            request = ChangeNicknameRequest(
                nickname = nickname,
            )
        )
    }

    override suspend fun fetchUserInformation(): User {
        return authAPI.fetchUserInformation().toModel()
    }
}
