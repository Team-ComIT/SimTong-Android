package com.comit.domain.usecase.users

import com.comit.domain.repository.AuthRepository
import javax.inject.Inject

class SaveDeviceTokenUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(token: String) = kotlin.runCatching {
        repository.saveDeviceToken(
            token = token,
        )
    }
}
