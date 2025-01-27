package com.dreamsoftware.artify.domain.repository

import com.dreamsoftware.artify.domain.exception.DeletePictureException
import com.dreamsoftware.artify.domain.exception.SavePictureException

interface IImageRepository {

    @Throws(SavePictureException::class)
    suspend fun save(path: String, name: String): String

    @Throws(DeletePictureException::class)
    suspend fun deleteByName(name: String)
}