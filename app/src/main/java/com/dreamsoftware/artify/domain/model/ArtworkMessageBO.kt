package com.dreamsoftware.artify.domain.model

data class ArtworkMessageBO(
    val uid: String,
    val role: ArtworkMessageRoleEnum,
    val text: String
)
