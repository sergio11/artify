package com.dreamsoftware.artify.domain.usecase

import com.dreamsoftware.brownie.core.BrownieUseCaseWithParams
import com.dreamsoftware.artify.domain.model.ArtworkBO
import com.dreamsoftware.artify.domain.repository.IArtworkRepository
import com.dreamsoftware.artify.domain.repository.IUserRepository

class GetArtworkByIdUseCase(
    private val userRepository: IUserRepository,
    private val artworkRepository: IArtworkRepository
) : BrownieUseCaseWithParams<GetArtworkByIdUseCase.Params, ArtworkBO>() {

    override suspend fun onExecuted(params: Params): ArtworkBO {
        val userId = userRepository.getUserAuthenticatedUid()
        return artworkRepository.fetchById(userId = userId, id = params.id)
    }

    data class Params(val id: String)
}