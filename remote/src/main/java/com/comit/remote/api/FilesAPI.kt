package com.comit.remote.api

import com.comit.remote.response.files.FileListResponse
import com.comit.remote.response.files.FileResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FilesAPI {

    @Multipart
    @POST("$FILES")
    suspend fun uploadFile(
        @Part("file") file: MultipartBody.Part,
    ): FileResponse

    @POST("$FILES/list")
    suspend fun uploadFileList(
        @Part("files") files: List<MultipartBody.Part>,
    ): FileListResponse

    private companion object {
        const val FILES = "files"
    }
}