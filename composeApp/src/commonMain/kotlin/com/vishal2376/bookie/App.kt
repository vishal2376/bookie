package com.vishal2376.bookie

import androidx.compose.runtime.Composable
import com.vishal2376.bookie.app.AppNavigation
import com.vishal2376.bookie.core.presentation.theme.BookieTheme
import org.koin.compose.KoinContext

@Composable
fun App(darkTheme: Boolean, dynamicColor: Boolean) {
	KoinContext {
		BookieTheme(darkTheme, dynamicColor) {
			AppNavigation()
		}
	}
}