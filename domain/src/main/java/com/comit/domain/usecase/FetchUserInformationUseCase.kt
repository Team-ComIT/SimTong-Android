package com.comit.domain.usecase

import com.comit.domain.repository.AuthRepository
import javax.inject.Inject

class FetchUserInformationUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        repository.fetchUserInformation()
    }
}
