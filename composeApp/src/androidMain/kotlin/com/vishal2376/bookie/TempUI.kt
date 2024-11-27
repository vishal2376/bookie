package com.vishal2376.bookie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.vishal2376.bookie.core.presentation.theme.BookieTheme


@Composable
fun TempUI() {

}

@Preview
@Composable
private fun TempUIPreview() {
	Column(
		modifier = Modifier.background(Color.White)
	) {
		BookieTheme(darkTheme = true, dynamicColor = false) {
			TempUI()
		}
	}
}