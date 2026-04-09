package dev.leonlatsch.photok.ads

import android.app.Activity
import android.content.Context
import android.view.ViewGroup

/**
 * No-op implementation of [AdsProvider] for FOSS variant.
 *
 * @since 1.6.0
 * @author Leon Latsch
 */
class NoOpAdsProvider : AdsProvider {
    override fun init(context: Context) {}
    override fun loadBanner(container: ViewGroup) {}
    override fun loadInterstitial(context: Context) {}
    override fun showInterstitial(activity: Activity) {}
}
