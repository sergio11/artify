package com.dreamsoftware.artify.ui.screens.detail

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.dreamsoftware.brownie.component.BrownieButton
import com.dreamsoftware.brownie.component.BrownieButtonStyleTypeEnum
import com.dreamsoftware.brownie.component.BrownieButtonTypeEnum
import com.dreamsoftware.brownie.component.BrownieDetailScreen
import com.dreamsoftware.brownie.component.BrownieDialog
import com.dreamsoftware.brownie.component.BrownieText
import com.dreamsoftware.brownie.component.BrownieTextTypeEnum
import com.dreamsoftware.artify.R
import com.dreamsoftware.artify.ui.components.LoadingDialog

@Composable
internal fun ArtworkDetailScreenContent(
    context: Context,
    density: Density,
    scrollState: ScrollState,
    uiState: ArtworkDetailUiState,
    actionListener: ArtworkDetailScreenActionListener
) {
    with(uiState) {
        LoadingDialog(isShowingDialog = isLoading)
        BrownieDialog(
            isVisible = showDeleteArtworkDialog,
            mainLogoRes = R.drawable.main_logo_inverse,
            titleRes = R.string.delete_artwork_dialog_title,
            descriptionRes = R.string.delete_artwork_dialog_description,
            cancelRes = R.string.delete_artwork_dialog_cancel,
            acceptRes = R.string.delete_artwork_dialog_accept,
            onCancelClicked = actionListener::onDeleteCancelled,
            onAcceptClicked = actionListener::onDeleteConfirmed,
        )
        BrownieDetailScreen(
            context = context,
            errorMessage = errorMessage,
            infoMessage = infoMessage,
            isLoading = isLoading,
            density = density,
            imageUrl = imageUrl,
            title = title,
            scrollState = scrollState,
            defaultImagePlaceholderRes = R.drawable.ic_splash_placeholder,
            backIconRes = R.drawable.ic_back,
            onBackClicked = actionListener::onBackPressed
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            BrownieText(
                type = BrownieTextTypeEnum.BODY_LARGE,
                titleRes = R.string.artwork_detail_user_question_title,
                textBold = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            BrownieText(
                type = BrownieTextTypeEnum.BODY_MEDIUM,
                titleText = title
            )
            Spacer(modifier = Modifier.height(10.dp))
            BrownieText(
                type = BrownieTextTypeEnum.BODY_LARGE,
                titleRes = R.string.artwork_detail_photo_context_title,
                textBold = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            BrownieText(
                type = BrownieTextTypeEnum.BODY_MEDIUM,
                titleText = description
            )
            Spacer(modifier = Modifier.height(30.dp))
            BrownieButton(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                type = BrownieButtonTypeEnum.LARGE,
                onClick = actionListener::onOpenChatClicked,
                textRes = R.string.artwork_detail_open_chat_text
            )
            BrownieButton(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                type = BrownieButtonTypeEnum.LARGE,
                style = BrownieButtonStyleTypeEnum.DANGER,
                onClick = actionListener::onArtworkDeleted,
                textRes = R.string.artwork_detail_delete_button_text
            )
        }
    }
}