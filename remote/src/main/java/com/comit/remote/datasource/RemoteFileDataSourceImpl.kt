package com.comit.remote.datasource

import com.comit.data.datasource.RemoteFileDataSource
import com.comit.remote.api.FilesAPI
import okhttp3.MultipartBody
import javax.inject.Inject

class RemoteFileDataSourceImpl @Inject constructor(
    private val repository: FilesAPI,
) : RemoteFileDataSource {

    override suspend fun uploadFile(
        file: MultipartBody.Part,
    ): String {
        return repository.uploadFile(
            file = file,
        ).filePath
    }

    override suspend fun uploadFileList(
        files: List<MultipartBody.Part>,
    ): List<String> {
        return repository.uploadFileList(
            files = files,
        ).filePathList
    }
}
