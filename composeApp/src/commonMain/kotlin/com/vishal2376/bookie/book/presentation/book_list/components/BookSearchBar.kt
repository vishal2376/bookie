package com.vishal2376.bookie.book.presentation.book_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import bookie.composeapp.generated.resources.Res
import bookie.composeapp.generated.resources.search_for_books
import org.jetbrains.compose.resources.stringResource


@Composable
fun BookSearchBar(
	searchQuery: String,
	onSearchQueryChange: (String) -> Unit,
	onSearch: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.widthIn(max = 400.dp)
			.fillMaxWidth()
			.padding(16.dp)
			.clip(RoundedCornerShape(16.dp))
			.background(MaterialTheme.colorScheme.primary),
		verticalAlignment = Alignment.CenterVertically
	) {
		TextField(
			modifier = Modifier.weight(1f).border(
				1.dp,
				MaterialTheme.colorScheme.primary,
				RoundedCornerShape(
					topEnd = 0.dp,
					bottomEnd = 0.dp,
					topStart = 16.dp,
					bottomStart = 16.dp
				)
			),
			shape = RoundedCornerShape(topEnd = 0.dp, bottomEnd = 0.dp),
			value = searchQuery,
			onValueChange = onSearchQueryChange,
			placeholder = {
				Text(text = stringResource(Res.string.search_for_books))
			},
			colors = TextFieldDefaults.colors(
				focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
				unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
				focusedIndicatorColor = Color.Transparent,
				disabledIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent,
				unfocusedTextColor = MaterialTheme.colorScheme.primary,
				focusedTextColor = MaterialTheme.colorScheme.primary,
			),
			singleLine = true,
			keyboardActions = KeyboardActions(
				onSearch = { onSearch() }
			),
			keyboardOptions = KeyboardOptions(
				keyboardType = KeyboardType.Text,
				imeAction = ImeAction.Search
			))
		IconButton(
			modifier = Modifier.padding(horizontal = 6.dp),
			onClick = onSearch
		) {
			Icon(
				imageVector = Icons.Outlined.Search,
				contentDescription = null,
				tint = MaterialTheme.colorScheme.onPrimary
			)
		}
	}
}

