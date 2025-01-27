package com.dreamsoftware.artify.data.repository.impl

import com.dreamsoftware.artify.data.remote.datasource.IImageDataSource
import com.dreamsoftware.artify.data.remote.exception.DeletePictureRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.SavePictureRemoteDataException
import com.dreamsoftware.artify.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.artify.domain.exception.DeletePictureException
import com.dreamsoftware.artify.domain.exception.SavePictureException
import com.dreamsoftware.artify.domain.repository.IImageRepository
import kotlinx.coroutines.CoroutineDispatcher

internal class ImageRepositoryImpl(
    private val imageDataSource: IImageDataSource,
    dispatcher: CoroutineDispatcher
): SupportRepositoryImpl(dispatcher), IImageRepository {

    @Throws(SavePictureException::class)
    override suspend fun save(path: String, name: String): String = safeExecute {
       try {
           imageDataSource.save(path, name)
       } catch (ex: SavePictureRemoteDataException) {
           throw SavePictureException("An error occurred when trying to save the picture", ex)
       }
    }

    override suspend fun deleteByName(name: String) = safeExecute {
        try {
            imageDataSource.deleteByName(name)
        } catch (ex: DeletePictureRemoteDataException) {
            throw DeletePictureException("An error occurred when trying to delete the picture", ex)
        }
    }
}