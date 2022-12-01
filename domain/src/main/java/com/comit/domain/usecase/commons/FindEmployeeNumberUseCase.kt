package com.comit.domain.usecase.commons

import com.comit.domain.repository.CommonsRepository
import javax.inject.Inject

class FindEmployeeNumberUseCase @Inject constructor(
    private val repository: CommonsRepository,
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.findEmployeeNumber(
            name = params.name,
            spotId = params.spotId,
            email = params.email,
        )
    }

    data class Params(
        val name: String,
        val spotId: String,
        val email: String,
    )
}