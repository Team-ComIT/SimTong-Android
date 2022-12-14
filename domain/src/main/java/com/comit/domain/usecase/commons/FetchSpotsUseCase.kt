package com.comit.domain.usecase.commons

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.CommonsRepository
import javax.inject.Inject

class FetchSpotsUseCase @Inject constructor(
    private val repository: CommonsRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        repository.fetchSpots()
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }
}
