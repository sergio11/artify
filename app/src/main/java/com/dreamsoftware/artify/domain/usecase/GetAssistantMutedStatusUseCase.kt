package com.dreamsoftware.artify.domain.usecase

import com.dreamsoftware.brownie.core.BrownieUseCase
import com.dreamsoftware.artify.domain.repository.IPreferenceRepository

class GetAssistantMutedStatusUseCase(
    private val preferencesRepository: IPreferenceRepository
) : BrownieUseCase<Boolean>() {

    override suspend fun onExecuted(): Boolean =
        preferencesRepository.isAssistantMuted()
}