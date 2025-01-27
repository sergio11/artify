package com.dreamsoftware.artify.domain.usecase

import com.dreamsoftware.brownie.core.BrownieUseCaseWithParams
import com.dreamsoftware.artify.domain.repository.IImageRepository
import com.dreamsoftware.artify.domain.repository.IInquizeRepository
import com.dreamsoftware.artify.domain.repository.IUserRepository

class DeleteInquizeByIdUseCase(
    private val userRepository: IUserRepository,
    private val imageRepository: IImageRepository,
    private val inquizeRepository: IInquizeRepository
) : BrownieUseCaseWithParams<DeleteInquizeByIdUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params): Unit = with(params) {
        val userId = userRepository.getUserAuthenticatedUid()
        imageRepository.deleteByName(id)
        inquizeRepository.deleteById(userId = userId, id = id)
    }

    data class Params(
        val id: String,
    )
}