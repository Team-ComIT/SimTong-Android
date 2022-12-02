package com.comit.data.datasource

import okhttp3.MultipartBody

interface RemoteFileDataSource {

    suspend fun uploadFile(
        file: MultipartBody.Part,
    ): String

    suspend fun uploadFileList(
        files: List<MultipartBody.Part>,
    ): List<String>
}