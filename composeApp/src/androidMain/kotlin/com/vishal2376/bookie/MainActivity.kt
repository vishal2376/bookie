package com.vishal2376.bookie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.vishal2376.bookie.di.initKoin

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			initKoin {}
			App(darkTheme = isSystemInDarkTheme(), dynamicColor = false)
		}
	}
}