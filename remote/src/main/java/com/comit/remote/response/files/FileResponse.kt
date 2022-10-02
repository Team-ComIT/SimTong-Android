package com.comit.remote.response.files

import com.google.gson.annotations.SerializedName

data class FileResponse(

    @field:SerializedName("file_path")
    val filePath: String,
)
