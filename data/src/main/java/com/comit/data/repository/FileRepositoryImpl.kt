package com.comit.data.repository

import com.comit.data.datasource.RemoteFileDataSource
import com.comit.data.extension.toMultipart
import com.comit.data.extension.toMultipartList
import com.comit.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val remoteFileDataSource: RemoteFileDataSource,
): FileRepository {
    override suspend fun uploadFile(
        file: File,
    ): String {
        return remoteFileDataSource.uploadFile(
            file = file.toMultipart(FORM_DATA_FILE_NAME)
        )
    }

    override suspend fun uploadFileList(
        files: List<File>,
    ): List<String> {
        return remoteFileDataSource.uploadFileList(
            files = files.toMultipartList(FORM_DATA_FILE_LIST_NAME)
        )
    }

    private companion object {
        const val FORM_DATA_FILE_NAME = "file"
        const val FORM_DATA_FILE_LIST_NAME = "files"
    }
}