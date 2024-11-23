package com.vishal2376.bookie.core.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val DarkColorScheme = darkColorScheme(
	primary = beige500,
	onPrimary = brown500,
	background = brown500,
	onBackground = beige200,
	primaryContainer = brown200,
	onPrimaryContainer = gray
)

val LightColorScheme = lightColorScheme(
	primary = brown200,
	onPrimary = beige500,
	background = beige200,
	onBackground = brown500,
	primaryContainer = beige500,
	onPrimaryContainer = gray
)


@Composable
expect fun BookieTheme(
	darkTheme: Boolean,
	dynamicColor: Boolean,
	content: @Composable () -> Unit
)