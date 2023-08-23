package com.chocolate.presentation.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import java.util.Locale


fun updateResources(context: Context, localeLanguage: Locale) {
    Locale.setDefault(localeLanguage)
    val resources: Resources = context.resources
    val config = Configuration(resources.configuration)

    config.setLocale(localeLanguage)
    context.createConfigurationContext(config)
    @Suppress("DEPRECATION")
    resources.updateConfiguration(config, resources.displayMetrics)

}
