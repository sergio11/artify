package com.dreamsoftware.artify.ui.screens.account.onboarding

import com.dreamsoftware.brownie.core.IBrownieScreenActionListener

interface OnboardingScreenActionListener: IBrownieScreenActionListener {
    fun onNavigateToSignIn()
    fun onNavigateToSignUp()
}