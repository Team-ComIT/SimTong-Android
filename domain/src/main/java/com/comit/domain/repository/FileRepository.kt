package com.comit.domain.repository

import java.io.File

interface FileRepository {

    suspend fun uploadFile(
        file: File,
    ): String

    suspend fun uploadFileList(
        files: List<File>,
    ): List<String>
}