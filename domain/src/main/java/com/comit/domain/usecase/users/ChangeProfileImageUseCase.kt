package com.comit.domain.usecase.users

import com.comit.domain.repository.AuthRepository
import javax.inject.Inject

class ChangeProfileImageUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.changeProfileImage(
            profileImagePath = params.profileImagePath,
        )
    }

    data class Params(
        val profileImagePath: String,
    )
}
