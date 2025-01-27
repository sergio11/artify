package com.dreamsoftware.artify.ui.screens.create

import com.dreamsoftware.brownie.core.IBrownieScreenActionListener

interface CreateInquizeScreenActionListener: IBrownieScreenActionListener {
    fun onStartListening()
    fun onUpdateQuestion(newQuestion: String)
    fun onCreateInquize()
    fun onCancelInquize()
}