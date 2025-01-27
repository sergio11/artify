package com.dreamsoftware.artify.di

import android.content.Context
import com.dreamsoftware.artify.domain.service.ISoundPlayerService
import com.dreamsoftware.artify.domain.service.ITTSService
import com.dreamsoftware.artify.domain.service.ITranscriptionService
import com.dreamsoftware.artify.framework.sound.SoundPlayerServiceImpl
import com.dreamsoftware.artify.framework.transcription.TranscriptionServiceImpl
import com.dreamsoftware.artify.framework.tts.TTSServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {

    @Provides
    @Singleton
    fun provideTranscriptionService(@ApplicationContext context: Context): ITranscriptionService = TranscriptionServiceImpl(context)

    @Provides
    @Singleton
    fun provideTextToSpeechService(@ApplicationContext context: Context): ITTSService = TTSServiceImpl(context)

    @Provides
    @Singleton
    fun provideSoundPlayerService(@ApplicationContext context: Context): ISoundPlayerService = SoundPlayerServiceImpl(context)
}