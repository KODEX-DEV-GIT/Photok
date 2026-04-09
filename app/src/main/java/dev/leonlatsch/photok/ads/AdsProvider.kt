package dev.leonlatsch.photok.ads

import android.app.Activity
import android.content.Context
import android.view.ViewGroup

/**
 * Interface for providing ads in the app.
 * Allows flavor-specific implementations.
 *
 * @since 1.6.0
 * @author Leon Latsch
 */
interface AdsProvider {
    /**
     * Initializes the ads SDK.
     */
    fun init(context: Context)

    /**
     * Loads a banner ad into the given [container].
     */
    fun loadBanner(container: ViewGroup)

    /**
     * Loads the interstitial ad.
     */
    fun loadInterstitial(context: Context)

    /**
     * Shows the interstitial ad if it is loaded.
     */
    fun showInterstitial(activity: Activity)
}
