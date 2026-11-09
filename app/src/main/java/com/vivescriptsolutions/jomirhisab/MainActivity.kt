package com.vivescriptsolutions.jomirhisab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vivescriptsolutions.jomirhisab.ads.AdManager
import com.vivescriptsolutions.jomirhisab.ads.ConsentManager
import com.vivescriptsolutions.jomirhisab.ui.JomirHisabScreen
import com.vivescriptsolutions.jomirhisab.ui.theme.JomirHisabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Gather UMP consent first; Mobile Ads only initializes with clearance.
        ConsentManager.gatherConsent(this) { canRequestAds ->
            if (canRequestAds) {
                AdManager.initialize(this)
            }
        }

        setContent {
            JomirHisabTheme {
                JomirHisabScreen()
            }
        }
    }
}
