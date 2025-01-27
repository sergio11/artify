package com.dreamsoftware.artify.domain.repository

import com.dreamsoftware.artify.domain.exception.AddArtworkMessageException
import com.dreamsoftware.artify.domain.exception.DeleteArtworkByIdException
import com.dreamsoftware.artify.domain.exception.FetchAllArtworkException
import com.dreamsoftware.artify.domain.exception.FetchArtworkByIdException
import com.dreamsoftware.artify.domain.exception.SaveArtworkException
import com.dreamsoftware.artify.domain.exception.SearchArtworkException
import com.dreamsoftware.artify.domain.model.AddArtworkMessageBO
import com.dreamsoftware.artify.domain.model.ArtworkBO
import com.dreamsoftware.artify.domain.model.CreateArtworkBO

interface IArtworkRepository {

    @Throws(SearchArtworkException::class)
    suspend fun search(userId: String, term: String): List<ArtworkBO>

    @Throws(SaveArtworkException::class)
    suspend fun create(data: CreateArtworkBO): ArtworkBO

    @Throws(AddArtworkMessageException::class)
    suspend fun addMessage(data: AddArtworkMessageBO): ArtworkBO

    @Throws(DeleteArtworkByIdException::class)
    suspend fun deleteById(userId: String, id: String)

    @Throws(FetchArtworkByIdException::class)
    suspend fun fetchById(userId: String, id: String): ArtworkBO

    @Throws(FetchAllArtworkException::class)
    suspend fun fetchAllByUserId(userId: String): List<ArtworkBO>
}