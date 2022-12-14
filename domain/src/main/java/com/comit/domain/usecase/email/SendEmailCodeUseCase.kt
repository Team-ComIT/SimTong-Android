package com.comit.domain.usecase.email

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.EmailRepository
import javax.inject.Inject

class SendEmailCodeUseCase @Inject constructor(
    private val repository: EmailRepository,
) {

    suspend operator fun invoke(
        email: String
    ) = kotlin.runCatching {
        repository.sendEmailCode(
            email = email,
        )
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }
}
