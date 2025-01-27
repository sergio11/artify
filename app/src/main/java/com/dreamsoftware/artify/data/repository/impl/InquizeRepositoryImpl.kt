package com.dreamsoftware.artify.data.repository.impl

import com.dreamsoftware.brownie.utils.IBrownieOneSideMapper
import com.dreamsoftware.artify.data.remote.datasource.IInquizeDataSource
import com.dreamsoftware.artify.data.remote.dto.AddInquizeMessageDTO
import com.dreamsoftware.artify.data.remote.dto.CreateInquizeDTO
import com.dreamsoftware.artify.data.remote.dto.InquizeDTO
import com.dreamsoftware.artify.data.remote.exception.AddInquizeMessageRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.CreateInquizeRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.DeleteInquizeByIdRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.FetchAllInquizeRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.FetchInquizeByIdRemoteDataException
import com.dreamsoftware.artify.data.remote.exception.SearchInquizeRemoteDataException
import com.dreamsoftware.artify.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.artify.domain.exception.AddInquizeMessageException
import com.dreamsoftware.artify.domain.exception.DeleteInquizeByIdException
import com.dreamsoftware.artify.domain.exception.FetchAllInquizeException
import com.dreamsoftware.artify.domain.exception.FetchInquizeByIdException
import com.dreamsoftware.artify.domain.exception.SaveInquizeException
import com.dreamsoftware.artify.domain.exception.SearchInquizeException
import com.dreamsoftware.artify.domain.model.AddInquizeMessageBO
import com.dreamsoftware.artify.domain.model.CreateInquizeBO
import com.dreamsoftware.artify.domain.model.InquizeBO
import com.dreamsoftware.artify.domain.repository.IInquizeRepository
import kotlinx.coroutines.CoroutineDispatcher

internal class InquizeRepositoryImpl(
    private val inquizeDataSource: IInquizeDataSource,
    private val saveInquizeMapper: IBrownieOneSideMapper<CreateInquizeBO, CreateInquizeDTO>,
    private val addInquizeMapper: IBrownieOneSideMapper<AddInquizeMessageBO, AddInquizeMessageDTO>,
    private val inquizeMapper: IBrownieOneSideMapper<InquizeDTO, InquizeBO>,
    dispatcher: CoroutineDispatcher
): SupportRepositoryImpl(dispatcher), IInquizeRepository {

    @Throws(SearchInquizeException::class)
    override suspend fun search(userId: String, term: String): List<InquizeBO> = safeExecute {
        try {
            inquizeDataSource.search(userId, term)
                .let(inquizeMapper::mapInListToOutList)
                .toList()
        } catch (ex: SearchInquizeRemoteDataException) {
            ex.printStackTrace()
            throw SearchInquizeException("An error occurred when searching content", ex)
        }
    }

    @Throws(SaveInquizeException::class)
    override suspend fun create(data: CreateInquizeBO): InquizeBO = safeExecute {
        try {
            with(inquizeDataSource) {
                create(saveInquizeMapper.mapInToOut(data))
                inquizeMapper.mapInToOut(fetchById(userId = data.userId, id = data.uid))
            }
        } catch (ex: CreateInquizeRemoteDataException) {
            ex.printStackTrace()
            throw SaveInquizeException("An error occurred when trying to save inquize", ex)
        }
    }

    @Throws(AddInquizeMessageException::class)
    override suspend fun addMessage(data: AddInquizeMessageBO): InquizeBO = safeExecute {
        try {
            with(inquizeDataSource) {
                addMessage(addInquizeMapper.mapInToOut(data))
                inquizeMapper.mapInToOut(fetchById(userId = data.userId, id = data.uid))
            }
        } catch (ex: AddInquizeMessageRemoteDataException) {
            ex.printStackTrace()
            throw AddInquizeMessageException("An error occurred when trying to save new message", ex)
        }
    }

    @Throws(DeleteInquizeByIdException::class)
    override suspend fun deleteById(userId: String, id: String) {
        safeExecute {
            try {
                inquizeDataSource.deleteById(userId = userId, id = id)
            } catch (ex: DeleteInquizeByIdRemoteDataException) {
                ex.printStackTrace()
                throw DeleteInquizeByIdException("An error occurred when trying to delete the inquize", ex)
            }
        }
    }

    @Throws(FetchInquizeByIdException::class)
    override suspend fun fetchById(userId: String, id: String): InquizeBO = safeExecute {
        try {
            inquizeDataSource.fetchById(userId = userId, id = id)
                .let(inquizeMapper::mapInToOut)
        } catch (ex: FetchInquizeByIdRemoteDataException) {
            ex.printStackTrace()
            throw FetchInquizeByIdException("An error occurred when trying to fetch the inquize data", ex)
        }
    }

    @Throws(FetchAllInquizeException::class)
    override suspend fun fetchAllByUserId(userId: String): List<InquizeBO> = safeExecute {
        try {
            inquizeDataSource.fetchAllByUserId(userId = userId)
                .let(inquizeMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchAllInquizeRemoteDataException) {
            ex.printStackTrace()
            throw FetchAllInquizeException("An error occurred when trying to fetch all user questions", ex)
        }
    }
}