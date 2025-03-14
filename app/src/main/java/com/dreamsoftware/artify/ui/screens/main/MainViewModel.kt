package com.dreamsoftware.artify.ui.screens.main

import androidx.lifecycle.viewModelScope
import com.dreamsoftware.brownie.component.BottomNavBarItem
import com.dreamsoftware.brownie.core.BrownieViewModel
import com.dreamsoftware.brownie.core.SideEffect
import com.dreamsoftware.brownie.core.UiState
import com.dreamsoftware.artify.R
import com.dreamsoftware.artify.ui.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BrownieViewModel<MainUiState, MainSideEffects>() {

    override fun onGetDefaultState(): MainUiState = MainUiState(mainDestinationList = listOf(
        BottomNavBarItem(
            route = Screens.Main.Home.Info.route,
            icon = R.drawable.icon_home,
            titleRes = R.string.home
        ),
        BottomNavBarItem(
            route = Screens.Main.Home.CreateArtwork.route,
            icon = R.drawable.ic_add_artwork,
            titleRes = R.string.create_artwork
        ),
        BottomNavBarItem(
            route = Screens.Main.Home.Settings.route,
            icon = R.drawable.icon_settings,
            titleRes = R.string.settings
        )
    ))

    fun onBottomItemsVisibilityChanged(hideBottomItems: Boolean) {
        viewModelScope.launch {
            updateState {
                it.copy(shouldShowBottomNav = !hideBottomItems)
            }
        }
    }
}

data class MainUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val shouldShowBottomNav: Boolean = false,
    val hasSession: Boolean = true,
    val mainDestinationList: List<BottomNavBarItem> = emptyList()
): UiState<MainUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): MainUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface MainSideEffects: SideEffect