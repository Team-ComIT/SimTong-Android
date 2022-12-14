package com.comit.domain.usecase.users

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.AuthRepository
import javax.inject.Inject

class FetchUserInformationUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        repository.fetchUserInformation()
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }
}
