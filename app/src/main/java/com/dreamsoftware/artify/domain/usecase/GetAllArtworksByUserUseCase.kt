package com.dreamsoftware.artify.domain.usecase

import com.dreamsoftware.brownie.core.BrownieUseCase
import com.dreamsoftware.artify.domain.model.ArtworkBO
import com.dreamsoftware.artify.domain.repository.IArtworkRepository
import com.dreamsoftware.artify.domain.repository.IUserRepository

class GetAllArtworksByUserUseCase(
    private val userRepository: IUserRepository,
    private val artworkRepository: IArtworkRepository
) : BrownieUseCase<List<ArtworkBO>>() {

    override suspend fun onExecuted(): List<ArtworkBO> =
        artworkRepository.fetchAllByUserId(userId = userRepository.getUserAuthenticatedUid())
}