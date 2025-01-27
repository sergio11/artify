package com.dreamsoftware.artify.domain.repository

import com.dreamsoftware.artify.domain.exception.CheckAuthenticatedException
import com.dreamsoftware.artify.domain.exception.CloseSessionException
import com.dreamsoftware.artify.domain.exception.SignInException
import com.dreamsoftware.artify.domain.exception.SignUpException
import com.dreamsoftware.artify.domain.model.AuthRequestBO
import com.dreamsoftware.artify.domain.model.AuthUserBO
import com.dreamsoftware.artify.domain.model.SignUpBO

interface IUserRepository {

    @Throws(CheckAuthenticatedException::class)
    suspend fun getCurrentAuthenticatedUser(): AuthUserBO

    @Throws(CheckAuthenticatedException::class)
    suspend fun getUserAuthenticatedUid(): String

    @Throws(SignInException::class)
    suspend fun signIn(authRequest: AuthRequestBO): AuthUserBO

    @Throws(SignUpException::class)
    suspend fun signUp(data: SignUpBO): AuthUserBO

    @Throws(CloseSessionException::class)
    suspend fun closeSession()
}