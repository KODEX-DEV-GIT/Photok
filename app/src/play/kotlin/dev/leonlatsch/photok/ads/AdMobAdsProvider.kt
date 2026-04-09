package dev.leonlatsch.photok.ads

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dev.leonlatsch.photok.R
import timber.log.Timber

/**
 * AdMob implementation of [AdsProvider].
 *
 * @since 1.6.0
 * @author Leon Latsch
 */
class AdMobAdsProvider : AdsProvider {

    private var interstitialAd: InterstitialAd? = null

    override fun init(context: Context) {
        MobileAds.initialize(context) {
            Timber.d("AdMob initialized")
        }
        loadInterstitial(context)
    }

    override fun loadBanner(container: ViewGroup) {
        val adView = AdView(container.context)
        adView.setAdSize(AdSize.BANNER)
        adView.adUnitId = container.context.getString(R.string.admob_banner_id)

        container.removeAllViews()
        container.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    override fun loadInterstitial(context: Context) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            context,
            context.getString(R.string.admob_interstitial_id),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    Timber.d("Interstitial ad loaded")
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    interstitialAd = null
                    Timber.d("Interstitial ad failed to load: ${error.message}")
                }
            }
        )
    }

    override fun showInterstitial(activity: Activity) {
        interstitialAd?.let {
            it.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    interstitialAd = null
                    loadInterstitial(activity)
                }

                override fun onAdFailedToShowFullScreenContent(error: AdError) {
                    interstitialAd = null
                    loadInterstitial(activity)
                }
            }
            it.show(activity)
        } ?: run {
            Timber.d("Interstitial ad not loaded yet")
            loadInterstitial(activity)
        }
    }
}
