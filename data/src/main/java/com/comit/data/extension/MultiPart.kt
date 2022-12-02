package com.comit.data.extension

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

internal fun File.toMultipart(
    fileName: String,
): MultipartBody.Part {
    val fileBody = RequestBody.create(MediaType.parse("image/jpeg"), this)
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
