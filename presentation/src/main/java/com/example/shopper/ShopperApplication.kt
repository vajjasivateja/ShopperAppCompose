package com.example.shopper

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.shopper.di.presentationModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class ShopperApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShopperApplication)
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    presentationModule,
                )
            )
        }
    }
}