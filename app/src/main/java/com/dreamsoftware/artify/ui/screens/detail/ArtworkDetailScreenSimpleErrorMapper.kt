package com.dreamsoftware.artify.ui.screens.detail

import android.content.Context
import com.dreamsoftware.brownie.core.IBrownieErrorMapper
import com.dreamsoftware.artify.R
import com.dreamsoftware.artify.domain.exception.InvalidDataException

class ArtworkDetailScreenSimpleErrorMapper(
    private val context: Context
): IBrownieErrorMapper {
    override fun mapToMessage(ex: Throwable): String = context.getString(when(ex) {
        is InvalidDataException -> R.string.generic_form_invalid_data_provided
        else -> R.string.generic_error_exception
    })
}