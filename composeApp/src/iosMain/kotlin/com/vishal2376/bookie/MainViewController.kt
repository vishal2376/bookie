package com.vishal2376.bookie

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
	App(darkTheme = isSystemDarkTheme(), dynamicColor = false)
}

private fun isSystemDarkTheme(): Boolean {
	return UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
}
