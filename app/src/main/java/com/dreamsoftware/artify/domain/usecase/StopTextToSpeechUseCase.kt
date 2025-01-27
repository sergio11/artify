package com.dreamsoftware.artify.domain.usecase

import com.dreamsoftware.brownie.core.BrownieUseCase
import com.dreamsoftware.artify.domain.service.ITTSService

class StopTextToSpeechUseCase(
    private val ttsService: ITTSService
) : BrownieUseCase<Unit>() {

    override suspend fun onExecuted() {
        ttsService.stop()
    }
}