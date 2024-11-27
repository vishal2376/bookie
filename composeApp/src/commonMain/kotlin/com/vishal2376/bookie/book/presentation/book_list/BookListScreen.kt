package com.vishal2376.bookie.book.presentation.book_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.book.presentation.book_list.components.BookSearchBar
import com.vishal2376.bookie.book.presentation.book_list.components.EmptyResultUI
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookListScreenRoot(
	viewModel: BookListViewModel = koinViewModel(),
	onBookClick: (Book) -> Unit,
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	BookListScreen(
		state = state,
		onAction = { action ->
			when (action) {
				is BookListAction.OnClickBook -> onBookClick(action.book)
				else -> Unit
			}
		}
	)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookListScreen(state: BookListState, onAction: (BookListAction) -> Unit) {
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
						text = "Bookie",
						style = MaterialTheme.typography.headlineLarge,
					)
				},
				actions = {
					IconButton(onClick = {
						// TODO: Navigate to Settings Screen
					}) {
						Icon(
							imageVector = Icons.Outlined.Settings,
							contentDescription = null
						)
					}
				})
		},
		floatingActionButton = {
			FloatingActionButton(
				onClick = {
					// TODO: Navigate to Bookmark Screen
				},
				containerColor = MaterialTheme.colorScheme.primary,
				contentColor = MaterialTheme.colorScheme.onPrimary
			) {
				Icon(imageVector = Icons.Outlined.Bookmarks, contentDescription = null)
			}
		}
	) { innerPadding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding),
		) {
			BookSearchBar(
				searchQuery = state.searchQuery,
				onSearchQueryChange = {},
				onSearch = {}
			)

			if (state.searchResults.isEmpty()) {
				EmptyResultUI()
			} else {
				//TODO: Display the search results
			}
		}
	}
}