package com.comit.domain.usecase.files

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

class UploadFileUseCase @Inject constructor(
    private val repository: FileRepository,
) {

    suspend operator fun invoke(
        file: File,
    ) = kotlin.runCatching {
        repository.uploadFile(
            file = file,
        )
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }
}
