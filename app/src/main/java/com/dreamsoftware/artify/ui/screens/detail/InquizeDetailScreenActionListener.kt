package com.dreamsoftware.artify.ui.screens.detail

import com.dreamsoftware.brownie.core.IBrownieScreenActionListener

interface InquizeDetailScreenActionListener: IBrownieScreenActionListener {
    fun onBackPressed()
    fun onOpenChatClicked()
    fun onInquizeDeleted()
    fun onDeleteInquizeConfirmed()
    fun onDeleteInquizeCancelled()
}