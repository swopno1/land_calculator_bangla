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
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
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
    private var lastInterstitialShowTime: Long = 0L
    private const val INTERSTITIAL_COOLDOWN_MS = 45_000L // At least 45 seconds between interstitials to comply with AdMob User Experience policy

    fun initialize(context: Context) {
        if (!isInitialized) {
            MobileAds.initialize(context) { initializationStatus ->
                Log.d(TAG, "MobileAds initialized: $initializationStatus")
                isInitialized = true
                loadInterstitial(context.applicationContext)
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
                    Log.d(TAG, "Interstitial loaded successfully")
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

    /**
     * Safely shows an interstitial ad if available and respect policy cooldown.
     * Always invokes [onAdClosed] when done or if no ad is ready.
     */
    fun showInterstitial(activity: Activity, onAdClosed: (() -> Unit)? = null) {
        val now = System.currentTimeMillis()
        val ad = interstitialAd

        if (ad != null && (now - lastInterstitialShowTime >= INTERSTITIAL_COOLDOWN_MS)) {
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Interstitial dismissed")
                    interstitialAd = null
                    lastInterstitialShowTime = System.currentTimeMillis()
                    loadInterstitial(activity.applicationContext)
                    onAdClosed?.invoke()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.w(TAG, "Interstitial failed to show: ${adError.message}")
                    interstitialAd = null
                    loadInterstitial(activity.applicationContext)
                    onAdClosed?.invoke()
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Interstitial showed full screen")
                }
            }
            ad.show(activity)
        } else {
            // Not ready or cooldown active; proceed with user action immediately
            if (ad == null) {
                loadInterstitial(activity.applicationContext)
            }
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
            .height(50.dp)
            .background(MaterialTheme.colorScheme.surface)
            .testTag("admob_banner_container"),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { adView },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
