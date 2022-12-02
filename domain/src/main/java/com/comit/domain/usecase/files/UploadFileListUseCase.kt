package com.comit.domain.usecase.files

import com.comit.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

class UploadFileListUseCase @Inject constructor(
    private val repository: FileRepository,
) {

    suspend operator fun invoke(
        files: List<File,>
    ) = kotlin.runCatching {
        repository.uploadFileList(
            files = files,
        )
    }
}
