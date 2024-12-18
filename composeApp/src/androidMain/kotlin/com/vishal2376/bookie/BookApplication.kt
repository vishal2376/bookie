package com.vishal2376.bookie

import android.app.Application
import com.vishal2376.bookie.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		initKoin {
			 androidContext(this@BookApplication)
		}
	}
}