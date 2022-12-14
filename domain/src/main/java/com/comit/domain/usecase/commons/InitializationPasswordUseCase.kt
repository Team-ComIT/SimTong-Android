package com.comit.domain.usecase.commons

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.CommonsRepository
import javax.inject.Inject

class InitializationPasswordUseCase @Inject constructor(
    private val repository: CommonsRepository,
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.initializationPassword(
            email = params.email,
            employeeNumber = params.employeeNumber,
            newPassword = params.newPassword,
        )
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }

    data class Params(
        val email: String,
        val employeeNumber: Int,
        val newPassword: String,
    )
}
