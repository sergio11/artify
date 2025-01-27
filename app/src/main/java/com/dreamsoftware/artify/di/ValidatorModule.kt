package com.dreamsoftware.artify.di

import android.content.Context
import com.dreamsoftware.artify.domain.model.AuthRequestBO
import com.dreamsoftware.artify.domain.model.SignUpBO
import com.dreamsoftware.artify.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.artify.domain.validation.ISignInValidationMessagesResolver
import com.dreamsoftware.artify.domain.validation.ISignUpValidationMessagesResolver
import com.dreamsoftware.artify.domain.validation.impl.SignInValidatorImpl
import com.dreamsoftware.artify.domain.validation.impl.SignUpValidatorImpl
import com.dreamsoftware.artify.ui.validation.SignInValidationMessagesResolverImpl
import com.dreamsoftware.artify.ui.validation.SignUpValidationMessagesResolverImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ValidatorModule {

    @Provides
    @ViewModelScoped
    fun provideSignUpValidationMessagesResolver(
        @ApplicationContext context: Context
    ): ISignUpValidationMessagesResolver = SignUpValidationMessagesResolverImpl(context)

    @Provides
    @ViewModelScoped
    fun provideSignInValidationMessagesResolver(
        @ApplicationContext context: Context
    ): ISignInValidationMessagesResolver = SignInValidationMessagesResolverImpl(context)

    @Provides
    @ViewModelScoped
    fun provideSignUpValidator(
        messagesResolver: ISignUpValidationMessagesResolver
    ): IBusinessEntityValidator<SignUpBO> = SignUpValidatorImpl(messagesResolver)

    @Provides
    @ViewModelScoped
    fun provideSignInValidator(
        messagesResolver: ISignInValidationMessagesResolver
    ): IBusinessEntityValidator<AuthRequestBO> = SignInValidatorImpl(messagesResolver)
}