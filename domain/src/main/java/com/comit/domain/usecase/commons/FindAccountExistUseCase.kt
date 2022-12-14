package com.comit.domain.usecase.commons

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.CommonsRepository
import javax.inject.Inject

class FindAccountExistUseCase @Inject constructor(
    private val repository: CommonsRepository,
) {

    suspend operator fun invoke(
        params: Params
    ) = kotlin.runCatching {
        repository.findAccountExist(
            employeeNumber = params.employeeNumber,
            email = params.email,
        )
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }

    data class Params(
        val employeeNumber: Int,
        val email: String,
    )
}
