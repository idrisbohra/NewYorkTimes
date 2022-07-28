package com.idriskhozema.nytimes.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Idris Khozema on 28/07/2022.
 */

@HiltAndroidApp
class NyTimesApp : Application() {

    override fun onCreate() { super.onCreate() }
}