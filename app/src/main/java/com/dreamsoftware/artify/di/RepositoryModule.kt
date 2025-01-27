package com.dreamsoftware.artify.di

import com.dreamsoftware.brownie.utils.IBrownieOneSideMapper
import com.dreamsoftware.artify.data.local.preferences.datasource.IPreferencesDataSource
import com.dreamsoftware.artify.data.remote.datasource.IAuthRemoteDataSource
import com.dreamsoftware.artify.data.remote.datasource.IImageDataSource
import com.dreamsoftware.artify.data.remote.datasource.IInquizeDataSource
import com.dreamsoftware.artify.data.remote.datasource.IMultiModalLanguageModelDataSource
import com.dreamsoftware.artify.data.remote.dto.AddInquizeMessageDTO
import com.dreamsoftware.artify.data.remote.dto.AuthUserDTO
import com.dreamsoftware.artify.data.remote.dto.InquizeDTO
import com.dreamsoftware.artify.data.remote.dto.ResolveQuestionDTO
import com.dreamsoftware.artify.data.remote.dto.CreateInquizeDTO
import com.dreamsoftware.artify.data.repository.impl.IMultiModalLanguageModelRepositoryImpl
import com.dreamsoftware.artify.data.repository.impl.ImageRepositoryImpl
import com.dreamsoftware.artify.data.repository.impl.InquizeRepositoryImpl
import com.dreamsoftware.artify.data.repository.impl.PreferenceRepositoryImpl
import com.dreamsoftware.artify.data.repository.impl.UserRepositoryImpl
import com.dreamsoftware.artify.data.repository.mapper.AddInquizeMessageMapper
import com.dreamsoftware.artify.data.repository.mapper.AuthUserMapper
import com.dreamsoftware.artify.data.repository.mapper.InquizeMapper
import com.dreamsoftware.artify.data.repository.mapper.ResolveQuestionMapper
import com.dreamsoftware.artify.data.repository.mapper.CreateInquizeMapper
import com.dreamsoftware.artify.domain.model.AddInquizeMessageBO
import com.dreamsoftware.artify.domain.model.AuthUserBO
import com.dreamsoftware.artify.domain.model.InquizeBO
import com.dreamsoftware.artify.domain.model.ResolveQuestionBO
import com.dreamsoftware.artify.domain.model.CreateInquizeBO
import com.dreamsoftware.artify.domain.repository.IImageRepository
import com.dreamsoftware.artify.domain.repository.IInquizeRepository
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
    fun provideInquizeMapper(): IBrownieOneSideMapper<InquizeDTO, InquizeBO> = InquizeMapper()

    @Provides
    @Singleton
    fun provideSaveInquizeMapper(): IBrownieOneSideMapper<CreateInquizeBO, CreateInquizeDTO> = CreateInquizeMapper()

    @Provides
    @Singleton
    fun provideResolveQuestionMapper(): IBrownieOneSideMapper<ResolveQuestionBO, ResolveQuestionDTO> = ResolveQuestionMapper()

    @Provides
    @Singleton
    fun provideAddInquizeMessageMapper(): IBrownieOneSideMapper<AddInquizeMessageBO, AddInquizeMessageDTO> = AddInquizeMessageMapper()

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
    fun provideInquizeRepository(
        inquizeDataSource: IInquizeDataSource,
        saveInquizeMapper: IBrownieOneSideMapper<CreateInquizeBO, CreateInquizeDTO>,
        addInquizeMapper: IBrownieOneSideMapper<AddInquizeMessageBO, AddInquizeMessageDTO>,
        inquizeMapper: IBrownieOneSideMapper<InquizeDTO, InquizeBO>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): IInquizeRepository =
        InquizeRepositoryImpl(
            inquizeDataSource,
            saveInquizeMapper,
            addInquizeMapper,
            inquizeMapper,
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
