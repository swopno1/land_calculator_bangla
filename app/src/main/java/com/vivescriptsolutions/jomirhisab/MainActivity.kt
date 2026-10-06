package com.vivescriptsolutions.jomirhisab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vivescriptsolutions.jomirhisab.ui.JomirHisabScreen
import com.vivescriptsolutions.jomirhisab.ui.theme.JomirHisabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize Google Mobile Ads SDK with test ads
        com.vivescriptsolutions.jomirhisab.ads.AdManager.initialize(this)

        setContent {
            JomirHisabTheme {
                JomirHisabScreen()
            }
        }
    }
}
