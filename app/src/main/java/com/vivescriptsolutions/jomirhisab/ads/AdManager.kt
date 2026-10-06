package com.vivescriptsolutions.jomirhisab.ads

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.vivescriptsolutions.jomirhisab.BuildConfig

object AdManager {
    private const val TAG = "AdManager"
    private var isInitialized = false
    private var interstitialAd: InterstitialAd? = null
    private var isInterstitialLoading = false

    fun initialize(context: Context) {
        if (!isInitialized) {
            MobileAds.initialize(context) { initializationStatus ->
                Log.d(TAG, "MobileAds initialized: $initializationStatus")
                isInitialized = true
                loadInterstitial(context)
            }
        }
    }

    fun loadInterstitial(context: Context) {
        if (isInterstitialLoading || interstitialAd != null) return

        isInterstitialLoading = true
        val adRequest = AdRequest.Builder().build()
        val adUnitId = BuildConfig.ADMOB_INTERSTITIAL_AD_UNIT_ID

        InterstitialAd.load(
            context,
            adUnitId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    Log.d(TAG, "Interstitial loaded")
                    interstitialAd = ad
                    isInterstitialLoading = false
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.w(TAG, "Interstitial failed to load: ${loadAdError.message}")
                    interstitialAd = null
                    isInterstitialLoading = false
                }
            }
        )
    }

    fun showInterstitial(activity: Activity, onAdClosed: (() -> Unit)? = null) {
        val ad = interstitialAd
        if (ad != null) {
            ad.show(activity)
            interstitialAd = null
            // Preload next interstitial
            loadInterstitial(activity)
            onAdClosed?.invoke()
        } else {
            // Not ready yet, just proceed
            loadInterstitial(activity)
            onAdClosed?.invoke()
        }
    }
}

/**
 * Clean, safe AdMob Banner Composable.
 * Shows an adaptive / standard banner at the bottom of the screen.
 */
@Composable
fun AdMobBannerView(
    modifier: Modifier = Modifier,
    adUnitId: String = BuildConfig.ADMOB_BANNER_AD_UNIT_ID
) {
    val context = LocalContext.current
    val adView = remember {
        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            this.adUnitId = adUnitId
            adListener = object : AdListener() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.w("AdMobBannerView", "Banner failed to load: ${loadAdError.message}")
                }
                override fun onAdLoaded() {
                    Log.d("AdMobBannerView", "Banner loaded successfully")
                }
            }
            loadAd(AdRequest.Builder().build())
        }
    }

    DisposableEffect(adView) {
        onDispose {
            adView.destroy()
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 4.dp)
            .testTag("admob_banner_container"),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { adView },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
