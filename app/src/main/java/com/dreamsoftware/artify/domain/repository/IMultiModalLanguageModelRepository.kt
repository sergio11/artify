package com.dreamsoftware.artify.domain.repository

import com.dreamsoftware.artify.domain.exception.GenerateImageDescriptionException
import com.dreamsoftware.artify.domain.exception.ResolveQuestionFromContextException
import com.dreamsoftware.artify.domain.model.ResolveQuestionBO

interface IMultiModalLanguageModelRepository {

    @Throws(ResolveQuestionFromContextException::class)
    suspend fun resolveQuestion(data: ResolveQuestionBO): String

    @Throws(GenerateImageDescriptionException::class)
    suspend fun generateImageDescription(imageUrl: String): String
}