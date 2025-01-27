package com.dreamsoftware.artify.domain.model

data class AddArtworkMessageBO(
    val uid: String,
    val userId: String,
    val question: String,
    val answer: String
)
