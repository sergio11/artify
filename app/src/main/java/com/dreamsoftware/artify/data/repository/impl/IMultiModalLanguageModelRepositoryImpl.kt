package com.dreamsoftware.artify.data.repository.impl

import com.dreamsoftware.brownie.utils.IBrownieOneSideMapper
import com.dreamsoftware.artify.data.remote.datasource.IMultiModalLanguageModelDataSource
import com.dreamsoftware.artify.data.remote.dto.ResolveQuestionDTO
import com.dreamsoftware.artify.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.artify.domain.exception.GenerateImageDescriptionException
import com.dreamsoftware.artify.domain.exception.ResolveQuestionFromContextException
import com.dreamsoftware.artify.domain.model.ResolveQuestionBO
import com.dreamsoftware.artify.domain.repository.IMultiModalLanguageModelRepository
import kotlinx.coroutines.CoroutineDispatcher

internal class IMultiModalLanguageModelRepositoryImpl(
    private val multiModalLanguageModelDataSource: IMultiModalLanguageModelDataSource,
    private val resolveQuestionMapper: IBrownieOneSideMapper<ResolveQuestionBO, ResolveQuestionDTO>,
    dispatcher: CoroutineDispatcher
): SupportRepositoryImpl(dispatcher), IMultiModalLanguageModelRepository {

    @Throws(ResolveQuestionFromContextException::class)
    override suspend fun resolveQuestion(data: ResolveQuestionBO): String = safeExecute {
        multiModalLanguageModelDataSource.resolveQuestionFromContext(resolveQuestionMapper.mapInToOut(data))
    }

    @Throws(GenerateImageDescriptionException::class)
    override suspend fun generateImageDescription(imageUrl: String): String = safeExecute {
        multiModalLanguageModelDataSource.generateImageDescription(imageUrl)
    }
}