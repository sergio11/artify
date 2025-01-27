package com.dreamsoftware.artify.ui.screens.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dreamsoftware.brownie.component.BrownieDialog
import com.dreamsoftware.artify.R
import com.dreamsoftware.artify.ui.navigation.graph.RootNavigationGraph

@Composable
fun AppScreenContent(
    navController: NavHostController,
    uiState: AppUiState,
    actionListener: AppScreenActionListener
) {
    with(uiState) {
        BrownieDialog(
            isVisible = false,
            mainLogoRes = R.drawable.main_logo_inverse,
            titleRes = R.string.generic_lost_network_connectivity_dialog_title,
            descriptionRes = R.string.generic_lost_network_connectivity_dialog_description,
            acceptRes = R.string.generic_lost_network_connectivity_dialog_open_settings_button_text,
            onAcceptClicked = actionListener::onOpenSettings,
        )
        RootNavigationGraph(navController = navController)
    }
}