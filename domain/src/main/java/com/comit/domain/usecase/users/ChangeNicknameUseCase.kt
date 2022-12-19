package com.comit.domain.usecase.users

import com.comit.domain.repository.AuthRepository
import javax.inject.Inject

class ChangeNicknameUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.changeNickname(
            nickname = params.nickname,
        )
    }

    data class Params(
        val nickname: String,
    )
}
