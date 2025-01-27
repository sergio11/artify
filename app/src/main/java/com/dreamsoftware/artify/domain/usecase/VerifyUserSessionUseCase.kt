package com.dreamsoftware.artify.domain.usecase

import com.dreamsoftware.brownie.core.BrownieUseCase
import com.dreamsoftware.artify.domain.model.AuthUserBO
import com.dreamsoftware.artify.domain.repository.IUserRepository

/**
 * VerifyUserSessionUseCase
 * @param userRepository
 */
class VerifyUserSessionUseCase(
    private val userRepository: IUserRepository
) : BrownieUseCase<AuthUserBO>() {

    override suspend fun onExecuted(): AuthUserBO = userRepository.getCurrentAuthenticatedUser()
}