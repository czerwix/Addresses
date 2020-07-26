package com.mobeedev.employees

import android.os.Handler
import android.os.Looper
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary.LeakCanaryFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader

import leakcanary.LeakCanary
import timber.log.Timber

class EmployeesAppDebug : EmployeesApp() {

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
//        initializeDebugTools()
        Timber.plant()
    }

    private fun initializeDebugTools() {
        with(AndroidFlipperClient.getInstance(this)) {
            addPlugin(
                InspectorFlipperPlugin(
                    applicationContext,
                    DescriptorMapping.withDefaults()
                )
            )
            addPlugin(DatabasesFlipperPlugin(applicationContext))
            addPlugin(LeakCanaryFlipperPlugin())
            addPlugin(
                SharedPreferencesFlipperPlugin(
                    applicationContext,
                    applicationContext.packageName + "_preferences"
                )
            )

            start()
        }

        Handler(Looper.getMainLooper()).apply {
            post {
                LeakCanary.config = LeakCanary.config.copy(
                    dumpHeap = true,
                    onHeapAnalyzedListener = FlipperLeakUploader(applicationContext)
                )
            }
        }
    }
}
