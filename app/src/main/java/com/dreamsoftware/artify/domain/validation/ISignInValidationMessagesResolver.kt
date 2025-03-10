package com.dreamsoftware.artify.domain.validation

interface ISignInValidationMessagesResolver {
    fun getInvalidEmailMessage(): String
    fun getShortPasswordMessage(minLength: Int): String
}