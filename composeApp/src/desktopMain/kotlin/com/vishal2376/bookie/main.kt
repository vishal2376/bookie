package com.vishal2376.bookie

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.vishal2376.bookie.di.initKoin
import io.ktor.client.engine.okhttp.OkHttp

fun main() {
	initKoin {}
	application {
		Window(
			onCloseRequest = ::exitApplication,
			title = "Bookie",
		) {
			App(darkTheme = isSystemInDarkTheme(), dynamicColor = false)
		}
	}
}