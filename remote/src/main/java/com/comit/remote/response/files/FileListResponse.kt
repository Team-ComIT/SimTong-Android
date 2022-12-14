package com.comit.remote.response.files

import com.google.gson.annotations.SerializedName

data class FileListResponse(
    @SerializedName("file_path_list")
    val filePathList: List<String>,
)
