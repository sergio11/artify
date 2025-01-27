package com.dreamsoftware.artify.di

import com.dreamsoftware.artify.domain.model.AuthRequestBO
import com.dreamsoftware.artify.domain.model.SignUpBO
import com.dreamsoftware.artify.domain.repository.IImageRepository
import com.dreamsoftware.artify.domain.repository.IInquizeRepository
import com.dreamsoftware.artify.domain.repository.IMultiModalLanguageModelRepository
import com.dreamsoftware.artify.domain.repository.IPreferenceRepository
import com.dreamsoftware.artify.domain.repository.IUserRepository
import com.dreamsoftware.artify.domain.service.ISoundPlayerService
import com.dreamsoftware.artify.domain.service.ITTSService
import com.dreamsoftware.artify.domain.service.ITranscriptionService
import com.dreamsoftware.artify.domain.usecase.AddInquizeMessageUseCase
import com.dreamsoftware.artify.domain.usecase.CreateInquizeUseCase
import com.dreamsoftware.artify.domain.usecase.DeleteInquizeByIdUseCase
import com.dreamsoftware.artify.domain.usecase.TranscribeUserQuestionUseCase
import com.dreamsoftware.artify.domain.usecase.EndUserSpeechCaptureUseCase
import com.dreamsoftware.artify.domain.usecase.GetAllInquizeByUserUseCase
import com.dreamsoftware.artify.domain.usecase.GetAssistantMutedStatusUseCase
import com.dreamsoftware.artify.domain.usecase.GetAuthenticateUserDetailUseCase
import com.dreamsoftware.artify.domain.usecase.GetInquizeByIdUseCase
import com.dreamsoftware.artify.domain.usecase.SearchInquizeUseCase
import com.dreamsoftware.artify.domain.usecase.SignInUseCase
import com.dreamsoftware.artify.domain.usecase.SignOffUseCase
import com.dreamsoftware.artify.domain.usecase.SignUpUseCase
import com.dreamsoftware.artify.domain.usecase.StopTextToSpeechUseCase
import com.dreamsoftware.artify.domain.usecase.TextToSpeechUseCase
import com.dreamsoftware.artify.domain.usecase.UpdateAssistantMutedStatusUseCase
import com.dreamsoftware.artify.domain.usecase.VerifyUserSessionUseCase
import com.dreamsoftware.artify.domain.validation.IBusinessEntityValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideVerifyUserSessionUseCase(
        userRepository: IUserRepository
    ): VerifyUserSessionUseCase =
        VerifyUserSessionUseCase(userRepository)

    @Provides
    @ViewModelScoped
    fun provideTranscribeUserQuestionUseCase(
        transcriptionService: ITranscriptionService
    ): TranscribeUserQuestionUseCase =
        TranscribeUserQuestionUseCase(transcriptionService)

    @Provides
    @ViewModelScoped
    fun provideEndUserSpeechCaptureUseCase(
        transcriptionService: ITranscriptionService
    ): EndUserSpeechCaptureUseCase =
        EndUserSpeechCaptureUseCase(transcriptionService)

    @Provides
    @ViewModelScoped
    fun provideSignInUseCase(
        userRepository: IUserRepository,
        preferenceRepository: IPreferenceRepository,
        validator: IBusinessEntityValidator<AuthRequestBO>
    ): SignInUseCase =
        SignInUseCase(
            userRepository = userRepository,
            preferenceRepository = preferenceRepository,
            validator = validator
        )

    @Provides
    @ViewModelScoped
    fun provideSignUpUseCase(
        preferenceRepository: IPreferenceRepository,
        userRepository: IUserRepository,
        validator: IBusinessEntityValidator<SignUpBO>
    ): SignUpUseCase =
        SignUpUseCase(
            userRepository = userRepository,
            preferenceRepository = preferenceRepository,
            validator = validator
        )

    @Provides
    @ViewModelScoped
    fun provideSignOffUseCase(
        userRepository: IUserRepository,
        preferenceRepository: IPreferenceRepository,
    ): SignOffUseCase =
        SignOffUseCase(
            userRepository = userRepository,
            preferenceRepository = preferenceRepository
        )

    @Provides
    @ViewModelScoped
    fun provideCreateInquizeUseCase(
        userRepository: IUserRepository,
        imageRepository: IImageRepository,
        inquizeRepository: IInquizeRepository,
        multiModalLanguageModelRepository: IMultiModalLanguageModelRepository
    ): CreateInquizeUseCase =
        CreateInquizeUseCase(
            userRepository = userRepository,
            imageRepository = imageRepository,
            inquizeRepository = inquizeRepository,
            multiModalLanguageModelRepository = multiModalLanguageModelRepository
        )

    @Provides
    @ViewModelScoped
    fun provideDeleteInquizeByIdUseCase(
        userRepository: IUserRepository,
        imageRepository: IImageRepository,
        inquizeRepository: IInquizeRepository
    ): DeleteInquizeByIdUseCase =
        DeleteInquizeByIdUseCase(
            userRepository = userRepository,
            imageRepository = imageRepository,
            inquizeRepository = inquizeRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetAllInquizeByUserUseCase(
        userRepository: IUserRepository,
        inquizeRepository: IInquizeRepository
    ): GetAllInquizeByUserUseCase =
        GetAllInquizeByUserUseCase(
            userRepository = userRepository,
            inquizeRepository = inquizeRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetAuthenticateUserDetailUseCase(
        userRepository: IUserRepository
    ): GetAuthenticateUserDetailUseCase =
        GetAuthenticateUserDetailUseCase(
            userRepository = userRepository,
        )

    @Provides
    @ViewModelScoped
    fun provideGetInquizeByIdUseCase(
        userRepository: IUserRepository,
        inquizeRepository: IInquizeRepository
    ): GetInquizeByIdUseCase =
        GetInquizeByIdUseCase(
            userRepository = userRepository,
            inquizeRepository = inquizeRepository
        )

    @Provides
    @ViewModelScoped
    fun provideTextToSpeechUseCase(
        ttsService: ITTSService
    ): TextToSpeechUseCase =
        TextToSpeechUseCase(
            ttsService = ttsService
        )

    @Provides
    @ViewModelScoped
    fun provideAddInquizeQuestionUseCase(
        userRepository: IUserRepository,
        inquizeRepository: IInquizeRepository,
        multiModalLanguageModelRepository: IMultiModalLanguageModelRepository
    ): AddInquizeMessageUseCase =
        AddInquizeMessageUseCase(
            userRepository = userRepository,
            inquizeRepository = inquizeRepository,
            multiModalLanguageModelRepository = multiModalLanguageModelRepository
        )


    @Provides
    @ViewModelScoped
    fun provideStopTextToSpeechUseCase(
        ttsService: ITTSService
    ): StopTextToSpeechUseCase =
        StopTextToSpeechUseCase(
            ttsService = ttsService
        )


    @Provides
    @ViewModelScoped
    fun provideUpdateAssistantMutedStatusUseCase(
        preferencesRepository: IPreferenceRepository,
        soundPlayerService: ISoundPlayerService
    ): UpdateAssistantMutedStatusUseCase =
        UpdateAssistantMutedStatusUseCase(
            preferencesRepository = preferencesRepository,
            soundPlayerService = soundPlayerService
        )

    @Provides
    @ViewModelScoped
    fun provideGetAssistantMutedStatusUseCase(
        preferencesRepository: IPreferenceRepository
    ): GetAssistantMutedStatusUseCase =
        GetAssistantMutedStatusUseCase(
            preferencesRepository = preferencesRepository
        )

    @Provides
    @ViewModelScoped
    fun provideSearchInquizeUseCase(
        userRepository: IUserRepository,
        inquizeRepository: IInquizeRepository,
    ): SearchInquizeUseCase =
        SearchInquizeUseCase(
            userRepository = userRepository,
            inquizeRepository = inquizeRepository
        )



}
