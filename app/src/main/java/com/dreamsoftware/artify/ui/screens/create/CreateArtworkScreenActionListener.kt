package com.dreamsoftware.artify.ui.screens.create

import com.dreamsoftware.brownie.core.IBrownieScreenActionListener

interface CreateArtworkScreenActionListener: IBrownieScreenActionListener {
    fun onStartListening()
    fun onUpdateQuestion(newQuestion: String)
    fun onCreate()
    fun onCancel()
}