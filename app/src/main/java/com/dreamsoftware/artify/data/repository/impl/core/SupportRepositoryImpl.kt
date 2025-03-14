package com.dreamsoftware.artify.data.repository.impl.core

import com.dreamsoftware.artify.domain.exception.DomainRepositoryException
import com.dreamsoftware.artify.domain.exception.RepositoryOperationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class SupportRepositoryImpl(
    private val dispatcher: CoroutineDispatcher
) {
    protected suspend fun <T> safeExecute(block: suspend () -> T): T = withContext(dispatcher) {
        try {
            block()
        }
        catch (ex: DomainRepositoryException) {
            ex.printStackTrace()
            throw ex
        }
        catch (ex: Exception) {
            ex.printStackTrace()
            throw RepositoryOperationException("Failed to execute operation", ex)
        }
    }
}