package com.dreamsoftware.artify.data.remote.datasource

import com.dreamsoftware.artify.data.remote.exception.DeletePictureRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.SavePictureRemoteDataException

interface IImageDataSource {

    @Throws(SavePictureRemoteDataException::class)
    suspend fun save(path: String, name: String): String

    @Throws(DeletePictureRemoteDataException::class)
    suspend fun deleteByName(name: String)
}
