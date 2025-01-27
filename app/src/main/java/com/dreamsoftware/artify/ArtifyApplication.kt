package com.dreamsoftware.artify

import android.app.Application
import com.dreamsoftware.brownie.utils.IBrownieAppEvent
import com.dreamsoftware.artify.utils.IApplicationAware
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ArtifyApplication : Application(), IApplicationAware

sealed interface AppEvent: IBrownieAppEvent {
    data object SignOff: AppEvent
}