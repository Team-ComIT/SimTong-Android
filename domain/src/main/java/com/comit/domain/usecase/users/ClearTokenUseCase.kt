package com.comit.domain.usecase.users

import com.comit.domain.repository.AuthRepository
import javax.inject.Inject

class ClearTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        authRepository.clearToken()
    }
}
