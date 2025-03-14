package com.dreamsoftware.artify.domain.usecase

import com.dreamsoftware.brownie.core.BrownieUseCaseWithParams
import com.dreamsoftware.artify.domain.repository.IPreferenceRepository
import com.dreamsoftware.artify.domain.service.ISoundPlayerService

class UpdateAssistantMutedStatusUseCase(
    private val preferencesRepository: IPreferenceRepository,
    private val soundPlayerService: ISoundPlayerService
) : BrownieUseCaseWithParams<UpdateAssistantMutedStatusUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean = with(params) {
        with(preferencesRepository) {
            setAssistantMutedStatus(isAssistantMuted)
            soundPlayerService.playSound(if(isAssistantMuted) {
                ISoundPlayerService.UISound.ASSISTANT_MUTED
            } else {
                ISoundPlayerService.UISound.ASSISTANT_UNMUTED
            })
            isAssistantMuted()
        }
    }

    data class Params(
        val isAssistantMuted: Boolean
    )
}