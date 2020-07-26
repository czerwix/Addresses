package com.mobeedev.employees

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.leakcanary.LeakCanaryFlipperPlugin
import leakcanary.DefaultOnHeapAnalyzedListener
import leakcanary.OnHeapAnalyzedListener
import shark.HeapAnalysis

/**
 * update after https://github.com/square/leakcanary/issues/1777
 */
class FlipperLeakUploader(val context: Context) : OnHeapAnalyzedListener {

    val defaultListener = DefaultOnHeapAnalyzedListener.create()

    override fun onHeapAnalyzed(heapAnalysis: HeapAnalysis) {
        val leakInfo: String = heapAnalysis.toString()

        val client = AndroidFlipperClient.getInstance(context)

        if (client != null) {
            val plugin =
                client.getPlugin<LeakCanaryFlipperPlugin>("LeakCanary")
            plugin?.reportLeak(leakInfo)
        }

        // Delegate to default behavior (notification and saving result)
        defaultListener.onHeapAnalyzed(heapAnalysis)
    }
}
