package com.comit.domain.usecase.users

import com.comit.domain.repository.AuthRepository
import java.io.File
import javax.inject.Inject

class ChangeProfileImageUseCase @Inject constructor(
    private val repository: AuthRepository,
) {

    suspend operator fun invoke(
        profileImg: File,
    ) = kotlin.runCatching {
        repository.changeProfileImage(
            profileImg = profileImg,
        )
    }
}
