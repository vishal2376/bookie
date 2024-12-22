package com.vishal2376.bookie.book.presentation.book_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@Composable
fun ChipInfoUI(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		Icon(
			modifier = Modifier
				.background(
					MaterialTheme.colorScheme.primaryContainer,
					RoundedCornerShape(8.dp)
				)
				.padding(4.dp),
			imageVector = icon,
			tint = MaterialTheme.colorScheme.primary,
			contentDescription = null
		)
		Text(
			text = text,
			style = MaterialTheme.typography.bodyMedium,
		)
	}
}