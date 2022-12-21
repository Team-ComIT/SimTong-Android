package com.comit.domain.usecase.commons

import com.comit.domain.repository.CommonsRepository
import javax.inject.Inject

class TokenReissueUseCase @Inject constructor(
    private val commonsRepository: CommonsRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        commonsRepository.tokenReissue()
    }
}
