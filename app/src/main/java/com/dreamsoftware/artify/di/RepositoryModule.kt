package com.dreamsoftware.artify.di

import com.dreamsoftware.brownie.utils.IBrownieOneSideMapper
import com.dreamsoftware.artify.data.local.preferences.datasource.IPreferencesDataSource
import com.dreamsoftware.artify.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.artify.data.remote.datasource.IImageDataSource
import com.dreamsoftware.artify.data.remote.datasource.IArtworkDataSource
import com.dreamsoftware.artify.data.remote.datasource.IMultiModalLanguageModelDataSource
import com.dreamsoftware.artify.data.remote.dto.AddArtworkMessageDTO
import com.dreamsoftware.artify.data.remote.dto.AuthUserDTO
import com.dreamsoftware.artify.data.remote.dto.ArtworkDTO
import com.dreamsoftware.artify.data.remote.dto.ResolveQuestionDTO
import com.dreamsoftware.artify.data.remote.dto.CreateArtworkDTO
import com.dreamsoftware.artify.data.repository.impl.IMultiModalLanguageModelRepositoryImpl
import com.dreamsoftware.artify.data.repository.impl.ImageRepositoryImpl
import com.dreamsoftware.artify.data.repository.impl.ArtworkRepositoryImpl
import com.dreamsoftware.artify.data.repository.impl.PreferenceRepositoryImpl
import com.dreamsoftware.artify.data.repository.impl.UserRepositoryImpl
import com.dreamsoftware.artify.data.repository.mapper.AddArtworkMessageMapper
import com.dreamsoftware.artify.data.repository.mapper.AuthUserMapper
import com.dreamsoftware.artify.data.repository.mapper.ArtworkMapper
import com.dreamsoftware.artify.data.repository.mapper.ResolveQuestionMapper
import com.dreamsoftware.artify.data.repository.mapper.CreateArtworkMapper
import com.dreamsoftware.artify.domain.model.AddArtworkMessageBO
import com.dreamsoftware.artify.domain.model.AuthUserBO
import com.dreamsoftware.artify.domain.model.ArtworkBO
import com.dreamsoftware.artify.domain.model.ResolveQuestionBO
import com.dreamsoftware.artify.domain.model.CreateArtworkBO
import com.dreamsoftware.artify.domain.repository.IImageRepository
import com.dreamsoftware.artify.domain.repository.IArtworkRepository
import com.dreamsoftware.artify.domain.repository.IMultiModalLanguageModelRepository
import com.dreamsoftware.artify.domain.repository.IPreferenceRepository
import com.dreamsoftware.artify.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthUserMapper(): IBrownieOneSideMapper<AuthUserDTO, AuthUserBO> = AuthUserMapper()

    @Provides
    @Singleton
    fun provideArtworkMapper(): IBrownieOneSideMapper<ArtworkDTO, ArtworkBO> = ArtworkMapper()

    @Provides
    @Singleton
    fun provideSaveArtworkMapper(): IBrownieOneSideMapper<CreateArtworkBO, CreateArtworkDTO> = CreateArtworkMapper()

    @Provides
    @Singleton
    fun provideResolveQuestionMapper(): IBrownieOneSideMapper<ResolveQuestionBO, ResolveQuestionDTO> = ResolveQuestionMapper()

    @Provides
    @Singleton
    fun provideAddArtworkMessageMapper(): IBrownieOneSideMapper<AddArtworkMessageBO, AddArtworkMessageDTO> = AddArtworkMessageMapper()

    @Provides
    @Singleton
    fun provideUserRepository(
        authDataSource: IAuthRemoteDataSource,
        authUserMapper: IBrownieOneSideMapper<AuthUserDTO, AuthUserBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IUserRepository =
        UserRepositoryImpl(
            authDataSource,
            authUserMapper,
            dispatcher
        )

    @Provides
    @Singleton
    fun providePreferenceRepository(
        preferenceDataSource: IPreferencesDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IPreferenceRepository =
        PreferenceRepositoryImpl(
            preferenceDataSource,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideArtworkRepository(
        artworkDataSource: IArtworkDataSource,
        saveArtworkMapper: IBrownieOneSideMapper<CreateArtworkBO, CreateArtworkDTO>,
        addArtworkMapper: IBrownieOneSideMapper<AddArtworkMessageBO, AddArtworkMessageDTO>,
        artworkMapper: IBrownieOneSideMapper<ArtworkDTO, ArtworkBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IArtworkRepository =
        ArtworkRepositoryImpl(
            artworkDataSource,
            saveArtworkMapper,
            addArtworkMapper,
            artworkMapper,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideMultiModalLanguageModelRepository(
        multiModalLanguageModelDataSource: IMultiModalLanguageModelDataSource,
        resolveQuestionMapper: IBrownieOneSideMapper<ResolveQuestionBO, ResolveQuestionDTO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IMultiModalLanguageModelRepository =
        IMultiModalLanguageModelRepositoryImpl(
            multiModalLanguageModelDataSource,
            resolveQuestionMapper,
            dispatcher
        )

    @Provides
    @Singleton
    fun provideImageRepository(
        imageDataSource: IImageDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IImageRepository =
        ImageRepositoryImpl(
            imageDataSource,
            dispatcher
        )
}
