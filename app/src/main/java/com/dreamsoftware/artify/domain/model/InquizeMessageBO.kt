package com.dreamsoftware.artify.domain.model

data class InquizeMessageBO(
    val uid: String,
    val role: InquizeMessageRoleEnum,
    val text: String
)
