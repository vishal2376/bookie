package com.vishal2376.bookie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vishal2376.bookie.core.presentation.theme.BookieTheme

@Composable
fun App(darkTheme: Boolean, dynamicColor: Boolean) {
	BookieTheme(darkTheme, dynamicColor) {
		Scaffold { innerPadding ->
			Column(
				modifier = Modifier.fillMaxSize().padding(innerPadding),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Text("Hello World")
			}
		}
	}
}