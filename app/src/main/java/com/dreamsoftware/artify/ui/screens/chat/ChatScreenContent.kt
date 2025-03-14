package com.dreamsoftware.artify.ui.screens.chat

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.dreamsoftware.brownie.component.BrownieDivider
import com.dreamsoftware.brownie.component.BrownieIconButton
import com.dreamsoftware.brownie.component.BrownieImageIcon
import com.dreamsoftware.brownie.component.BrownieImageSize
import com.dreamsoftware.brownie.component.BrownieSheetSurface
import com.dreamsoftware.brownie.component.BrownieType
import com.dreamsoftware.brownie.component.screen.BrownieScreenContent
import com.dreamsoftware.brownie.utils.EMPTY
import com.dreamsoftware.artify.R
import com.dreamsoftware.artify.domain.model.ArtworkMessageBO
import com.dreamsoftware.artify.domain.model.ArtworkMessageRoleEnum
import com.dreamsoftware.artify.ui.components.AnimatedMicButtonWithTranscript
import com.dreamsoftware.artify.ui.components.ChatMessageCard
import com.dreamsoftware.artify.ui.components.LoadingDialog
import com.dreamsoftware.artify.ui.components.Role
import com.dreamsoftware.artify.ui.theme.ArtifyTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChatScreenContent(
    uiState: ChatUiState,
    actionListener: ChatScreenActionListener,
) {
    with(uiState) {
        with(MaterialTheme.colorScheme) {
            val chatScreenHaptics = rememberChatScreenHaptics()
            LoadingDialog(isShowingDialog = isLoading)
            BrownieScreenContent(
                hasTopBar = false,
                errorMessage = errorMessage,
                infoMessage = infoMessage,
                enableVerticalScroll = true,
                screenContainerColor = primary,
                onInfoMessageCleared = actionListener::onInfoMessageCleared,
                onErrorMessageCleared = actionListener::onErrorMessageCleared,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BrownieImageIcon(
                        modifier = Modifier.clickable {
                            actionListener.onBackButtonClicked()
                        },
                        type = BrownieType.ICON,
                        size = BrownieImageSize.NORMAL,
                        iconRes = R.drawable.ic_back,
                        tintColor = onPrimary
                    )
                    Image(
                        painter = painterResource(id = R.drawable.main_logo_inverse),
                        contentDescription = String.EMPTY,
                        modifier = Modifier
                            .height(70.dp)
                            .padding(bottom = 8.dp)
                    )
                    SoundToggleButton(
                        isAssistantMuted = isAssistantMuted,
                        onAssistantMutedChange = actionListener::onAssistantMutedChange
                    )
                }

                BrownieSheetSurface(
                    enableVerticalScroll = false,
                    verticalArrangement = Arrangement.Top
                ) {
                    // Get the screen height from LocalConfiguration
                    val configuration = LocalConfiguration.current
                    val screenHeight = configuration.screenHeightDp.dp
                    Spacer(modifier = Modifier.height(8.dp))
                    ChatMessagesList(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(screenHeight * 0.7f) // Set 70% of the screen height
                            .padding(
                                start = 4.dp, end = 4.dp,
                                top = 8.dp, bottom = 0.dp
                            ),
                        messageList = messageList
                    )
                    BrownieDivider(color = primary)
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(primaryContainer)) {
                        AnimatedContent(
                            modifier = Modifier
                                .align(Alignment.Center),
                            targetState = isAssistantResponseLoading,
                            label = String.EMPTY
                        ) { isResponseLoading ->
                            if (isResponseLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .background(secondary)
                                        .padding(16.dp)
                                        .size(64.dp)
                                )
                            } else if (isAssistantSpeaking) {
                                Column(
                                    modifier = Modifier.padding(10.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    BrownieIconButton(
                                        iconSize = 100.dp,
                                        containerSize = 100.dp,
                                        containerColor = onSecondary,
                                        iconRes = R.drawable.baseline_stop_circle_24,
                                        iconTintColor = secondary
                                    ) {
                                        chatScreenHaptics.provideStopAssistantSpeechHapticFeedback()
                                        actionListener.onAssistantSpeechStopped()
                                    }
                                }

                            } else {
                                AnimatedMicButtonWithTranscript(
                                    userTextTranscription = lastQuestion,
                                    isListening = isListening,
                                    onStartListening = actionListener::onStartListening
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChatMessagesList(
    modifier: Modifier = Modifier,
    messageList: List<ArtworkMessageBO>
) {
    val lazyListState = rememberLazyListState()
    if(messageList.isNotEmpty()) {
        LaunchedEffect(messageList) {
            lazyListState.animateScrollToItem(messageList.lastIndex)
        }
    }
    LazyColumn(
        modifier = modifier.fillMaxHeight(), // Ensures clear height constraints
        state = lazyListState
    ) {
        items(items = messageList, key = { it.uid }) { chatMessage ->
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ChatMessageCard(
                    modifier = Modifier
                        .align(
                            if (chatMessage.role == ArtworkMessageRoleEnum.USER) {
                                Alignment.CenterEnd
                            } else {
                                Alignment.CenterStart
                            }
                        )
                        .widthIn(max = this.maxWidth / 1.5f),
                    messageContent = chatMessage.text,
                    role = if (chatMessage.role == ArtworkMessageRoleEnum.USER) {
                        Role.USER
                    }  else {
                        Role.RESPONDER
                    }
                )
            }
        }
    }
}

@Composable
private fun SoundToggleButton(
    isAssistantMuted: Boolean,
    onAssistantMutedChange: (isMuted: Boolean) -> Unit
) {
    val chatScreenHaptics = rememberChatScreenHaptics()
    with(MaterialTheme.colorScheme) {
        BrownieIconButton(
            containerSize = 40.dp,
            containerColor = onPrimary,
            iconTintColor = primary,
            iconRes = if(isAssistantMuted) {
                R.drawable.baseline_volume_off_24
            } else {
                R.drawable.baseline_volume_up_24
            }
        ) {
            onAssistantMutedChange(!isAssistantMuted)
            with(chatScreenHaptics) {
                if(isAssistantMuted) {
                    provideUnMutedHapticFeedback()
                } else {
                    provideMutedHapticFeedback()
                }
            }
        }
    }
}


@Composable
private fun rememberChatScreenHaptics(
    scope: CoroutineScope = rememberCoroutineScope(),
    localHapticFeedback: HapticFeedback = LocalHapticFeedback.current
): ChatScreenHaptics = remember(scope, localHapticFeedback) {
    ChatScreenHaptics(scope, localHapticFeedback)
}

private class ChatScreenHaptics(
    private val scope: CoroutineScope,
    private val localHapticFeedback: HapticFeedback
) {
    fun provideMutedHapticFeedback() {
        scope.launch {
            repeat(2) {
                localHapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                delay(50)
            }
        }
    }

    fun provideUnMutedHapticFeedback() {
        localHapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
    }

    fun provideStopAssistantSpeechHapticFeedback() {
        scope.launch {
            with(localHapticFeedback) {
                performHapticFeedback(HapticFeedbackType.LongPress)
                delay(5)
                performHapticFeedback(HapticFeedbackType.LongPress)
            }
        }
    }
}


@PreviewLightDark
@PreviewDynamicColors
@Composable
private fun ChatScreenPreview() {
    ArtifyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

        }
    }
}
