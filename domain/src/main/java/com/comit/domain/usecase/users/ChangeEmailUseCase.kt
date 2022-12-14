package com.comit.domain.usecase.users

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.AuthRepository
import javax.inject.Inject

class ChangeEmailUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.changeEmail(
            email = params.email,
        )
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }

    data class Params(
        val email: String,
    )
}
