package com.comit.remote.request.files

import com.google.gson.annotations.SerializedName
import java.io.File

data class FileRequest(

    @field:SerializedName("file")
    val file: File,
)
