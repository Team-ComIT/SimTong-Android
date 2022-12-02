package com.comit.domain.usecase.users

import com.comit.domain.repository.AuthRepository
import java.util.UUID
import javax.inject.Inject

class ChangeSpotUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.changeSpot(
            spotId = params.spotId,
        )
    }

    data class Params(
        val spotId: UUID,
    )
}
