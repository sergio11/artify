package com.dreamsoftware.artify.di

import com.dreamsoftware.artify.domain.model.AuthRequestBO
import com.dreamsoftware.artify.domain.model.SignUpBO
import com.dreamsoftware.artify.domain.repository.IImageRepository
import com.dreamsoftware.artify.domain.repository.IArtworkRepository
import com.dreamsoftware.artify.domain.repository.IMultiModalLanguageModelRepository
import com.dreamsoftware.artify.domain.repository.IPreferenceRepository
import com.dreamsoftware.artify.domain.repository.IUserRepository
import com.dreamsoftware.artify.domain.service.ISoundPlayerService
import com.dreamsoftware.artify.domain.service.ITTSService
import com.dreamsoftware.artify.domain.service.ITranscriptionService
import com.dreamsoftware.artify.domain.usecase.AddArtworkMessageUseCase
import com.dreamsoftware.artify.domain.usecase.CreateArtworkUseCase
import com.dreamsoftware.artify.domain.usecase.DeleteArtworkByIdUseCase
import com.dreamsoftware.artify.domain.usecase.TranscribeUserQuestionUseCase
import com.dreamsoftware.artify.domain.usecase.EndUserSpeechCaptureUseCase
import com.dreamsoftware.artify.domain.usecase.GetAllArtworksByUserUseCase
import com.dreamsoftware.artify.domain.usecase.GetAssistantMutedStatusUseCase
import com.dreamsoftware.artify.domain.usecase.GetAuthenticateUserDetailUseCase
import com.dreamsoftware.artify.domain.usecase.GetArtworkByIdUseCase
import com.dreamsoftware.artify.domain.usecase.SearchArtworkUseCase
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
    fun provideCreateArtworkUseCase(
        userRepository: IUserRepository,
        imageRepository: IImageRepository,
        artworkRepository: IArtworkRepository,
        multiModalLanguageModelRepository: IMultiModalLanguageModelRepository
    ): CreateArtworkUseCase =
        CreateArtworkUseCase(
            userRepository = userRepository,
            imageRepository = imageRepository,
            artworkRepository = artworkRepository,
            multiModalLanguageModelRepository = multiModalLanguageModelRepository
        )

    @Provides
    @ViewModelScoped
    fun provideDeleteArtworkByIdUseCase(
        userRepository: IUserRepository,
        imageRepository: IImageRepository,
        artworkRepository: IArtworkRepository
    ): DeleteArtworkByIdUseCase =
        DeleteArtworkByIdUseCase(
            userRepository = userRepository,
            imageRepository = imageRepository,
            artworkRepository = artworkRepository
        )

    @Provides
    @ViewModelScoped
    fun provideGetAllArtworkByUserUseCase(
        userRepository: IUserRepository,
        artworkRepository: IArtworkRepository
    ): GetAllArtworksByUserUseCase =
        GetAllArtworksByUserUseCase(
            userRepository = userRepository,
            artworkRepository = artworkRepository
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
    fun provideGetArtworkByIdUseCase(
        userRepository: IUserRepository,
        artworkRepository: IArtworkRepository
    ): GetArtworkByIdUseCase =
        GetArtworkByIdUseCase(
            userRepository = userRepository,
            artworkRepository = artworkRepository
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
    fun provideAddArtworkQuestionUseCase(
        userRepository: IUserRepository,
        artworkRepository: IArtworkRepository,
        multiModalLanguageModelRepository: IMultiModalLanguageModelRepository
    ): AddArtworkMessageUseCase =
        AddArtworkMessageUseCase(
            userRepository = userRepository,
            artworkRepository = artworkRepository,
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
    fun provideSearchArtworkUseCase(
        userRepository: IUserRepository,
        artworkRepository: IArtworkRepository,
    ): SearchArtworkUseCase =
        SearchArtworkUseCase(
            userRepository = userRepository,
            artworkRepository = artworkRepository
        )
}
