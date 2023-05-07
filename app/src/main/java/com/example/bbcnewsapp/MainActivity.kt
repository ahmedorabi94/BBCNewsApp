package com.example.bbcnewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.biometric.BiometricPrompt
import com.example.bbcnewsapp.features.biometric_view.fingerprints.common.BiometricAuthListener
import com.example.bbcnewsapp.features.biometric_view.fingerprints.util.BiometricUtil
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}