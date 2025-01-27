package com.dreamsoftware.artify.ui.screens.home

import com.dreamsoftware.brownie.core.IBrownieScreenActionListener
import com.dreamsoftware.artify.domain.model.InquizeBO

interface HomeScreenActionListener: IBrownieScreenActionListener {
    fun onInquizeClicked(inquizeBO: InquizeBO)
    fun onInquizeDetailClicked(inquizeBO: InquizeBO)
    fun onSearchQueryUpdated(newSearchQuery: String)
    fun onInquizeDeleted(inquizeBO: InquizeBO)
    fun onDeleteInquizeConfirmed()
    fun onDeleteInquizeCancelled()
}