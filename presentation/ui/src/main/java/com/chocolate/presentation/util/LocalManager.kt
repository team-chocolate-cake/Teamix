package com.chocolate.presentation.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import java.util.Locale


fun updateResources(context: Context, localeLanguage: Locale) {
    Locale.setDefault(localeLanguage)
    val resources: Resources = context.resources
    val config = Configuration(resources.configuration)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        config.setLocale(localeLanguage)
    else
        config.locale = localeLanguage


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        context.createConfigurationContext(config)
    resources.updateConfiguration(config, resources.displayMetrics)


}
