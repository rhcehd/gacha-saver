package dev.rhcehd123.samplegame

import android.app.Application
import com.google.android.gms.games.PlayGamesSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        PlayGamesSdk.initialize(this)
    }
}
