package com.dreamsoftware.artify.domain.repository

import com.dreamsoftware.artify.domain.exception.AddInquizeMessageException
import com.dreamsoftware.artify.domain.exception.DeleteInquizeByIdException
import com.dreamsoftware.artify.domain.exception.FetchAllInquizeException
import com.dreamsoftware.artify.domain.exception.FetchInquizeByIdException
import com.dreamsoftware.artify.domain.exception.SaveInquizeException
import com.dreamsoftware.artify.domain.exception.SearchInquizeException
import com.dreamsoftware.artify.domain.model.AddInquizeMessageBO
import com.dreamsoftware.artify.domain.model.InquizeBO
import com.dreamsoftware.artify.domain.model.CreateInquizeBO

interface IInquizeRepository {

    @Throws(SearchInquizeException::class)
    suspend fun search(userId: String, term: String): List<InquizeBO>

    @Throws(SaveInquizeException::class)
    suspend fun create(data: CreateInquizeBO): InquizeBO

    @Throws(AddInquizeMessageException::class)
    suspend fun addMessage(data: AddInquizeMessageBO): InquizeBO

    @Throws(DeleteInquizeByIdException::class)
    suspend fun deleteById(userId: String, id: String)

    @Throws(FetchInquizeByIdException::class)
    suspend fun fetchById(userId: String, id: String): InquizeBO

    @Throws(FetchAllInquizeException::class)
    suspend fun fetchAllByUserId(userId: String): List<InquizeBO>
}