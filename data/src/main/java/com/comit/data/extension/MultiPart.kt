package com.comit.data.extension

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

internal fun File.toMultipart(
    fileName: String,
): MultipartBody.Part {
    val fileBody = this.asRequestBody("application/json".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(fileName, this.name, fileBody)
}

internal fun List<File>.toMultipartList(
    fileName: String,
): List<MultipartBody.Part> =
    this.map {
        it.toMultipart(
            fileName = fileName,
        )
    }
