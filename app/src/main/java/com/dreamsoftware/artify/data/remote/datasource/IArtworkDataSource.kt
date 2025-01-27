package com.dreamsoftware.artify.data.remote.datasource

import com.dreamsoftware.artify.data.remote.dto.AddArtworkMessageDTO
import com.dreamsoftware.artify.data.remote.dto.CreateArtworkDTO
import com.dreamsoftware.artify.data.remote.dto.ArtworkDTO
import com.dreamsoftware.artify.data.remote.exception.AddArtworkMessageRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.DeleteArtworkByIdRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.FetchAllArtworkRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.FetchArtworkByIdRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.CreateArtworkRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.SearchArtworkRemoteDataException

interface IArtworkDataSource {

    @Throws(SearchArtworkRemoteDataException::class)
    suspend fun search(userId: String, term: String): List<ArtworkDTO>

    @Throws(CreateArtworkRemoteDataException::class)
    suspend fun create(data: CreateArtworkDTO)

    @Throws(AddArtworkMessageRemoteDataException::class)
    suspend fun addMessage(data: AddArtworkMessageDTO)

    @Throws(FetchArtworkByIdRemoteDataException::class)
    suspend fun fetchById(userId: String, id: String): ArtworkDTO

    @Throws(FetchAllArtworkRemoteDataException::class)
    suspend fun fetchAllByUserId(userId: String): List<ArtworkDTO>

    @Throws(DeleteArtworkByIdRemoteDataException::class)
    suspend fun deleteById(userId: String, id: String)
}