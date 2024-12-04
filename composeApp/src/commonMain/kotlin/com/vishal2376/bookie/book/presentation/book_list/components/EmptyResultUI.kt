package com.vishal2376.bookie.book.presentation.book_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import bookie.composeapp.generated.resources.Res
import bookie.composeapp.generated.resources.empty_result_dark
import bookie.composeapp.generated.resources.no_bookies_here
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmptyResultUI(
	message: String = stringResource(Res.string.no_bookies_here),
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(bottom = 72.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
	) {
		Image(
			modifier = Modifier.size(250.dp),
			painter = painterResource(Res.drawable.empty_result_dark),
			contentDescription = null
		)
		Text(
			text = message,
			fontWeight = FontWeight.Bold,
			style = MaterialTheme.typography.headlineSmall
		)
	}
}