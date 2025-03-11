package com.vishal2376.bookie.book.presentation.book_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vishal2376.bookie.book.presentation.book_detail.components.BookDetailUI
import com.vishal2376.bookie.book.presentation.book_list.components.EmptyResultUI

@Composable
fun BookDetailScreenRoot(viewModel: BookDetailViewModel, onClickBack: () -> Unit) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	BookDetailScreen(state = state, onAction = { action ->
		when (action) {
			BookDetailAction.OnClickBack -> onClickBack()
			else -> Unit
		}
		viewModel.onAction(action)
	})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailScreen(state: BookDetailState, onAction: (BookDetailAction) -> Unit) {
	Scaffold(
		topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = MaterialTheme.colorScheme.background,
					titleContentColor = MaterialTheme.colorScheme.primary,
					actionIconContentColor = MaterialTheme.colorScheme.primary
				),
				title = {
					Text(
						text = "Back",
						style = MaterialTheme.typography.titleLarge,
					)
				},
				navigationIcon = {
					IconButton(onClick = {
						onAction(BookDetailAction.OnClickBack)
					}) {
						Icon(
							imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
							tint = MaterialTheme.colorScheme.primary,
							contentDescription = null
						)
					}
				},
				actions = {
					IconButton(onClick = {
						onAction(BookDetailAction.OnClickFavorite)
					}) {
						Icon(
							imageVector = if (state.isFavorite) Icons.Outlined.Bookmark else Icons.Outlined.BookmarkBorder,
							contentDescription = null
						)
					}
				})
		}
	) { innerPadding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding),
		) {
			if (state.book == null) {
				EmptyResultUI()
			} else {
				BookDetailUI(state.book)
			}
		}
	}
}
