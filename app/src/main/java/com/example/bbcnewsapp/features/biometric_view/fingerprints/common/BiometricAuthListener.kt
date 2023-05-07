package com.example.bbcnewsapp.features.biometric_view.fingerprints.common

import androidx.biometric.BiometricPrompt


interface BiometricAuthListener {
  fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult)
  fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String)
}
