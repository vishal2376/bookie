package com.vishal2376.bookie.book.presentation.book_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bookie.composeapp.generated.resources.Res
import bookie.composeapp.generated.resources.app_name
import bookie.composeapp.generated.resources.search_results
import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.book.presentation.book_list.components.BookSearchBar
import com.vishal2376.bookie.book.presentation.book_list.components.EmptyResultUI
import com.vishal2376.bookie.book.presentation.components.BookListUI
import com.vishal2376.bookie.book.presentation.components.HeadingTextUI
import org.jetbrains.compose.resources.stringResource
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
			viewModel.onAction(action)
		}
	)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookListScreen(state: BookListState, onAction: (BookListAction) -> Unit) {

	val keyboardController = LocalSoftwareKeyboardController.current
	val lazyStaggeredGridState = rememberLazyStaggeredGridState()

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
						text = stringResource(Res.string.app_name),
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
				modifier = Modifier.align(Alignment.CenterHorizontally),
				searchQuery = state.searchQuery,
				onSearchQueryChange = { onAction(BookListAction.OnSearchQueryChange(it)) },
				onSearch = { keyboardController?.hide() }
			)

			if (state.isLoading) {
				Box(
					modifier = Modifier.fillMaxSize(),
					contentAlignment = Alignment.Center
				) {
					CircularProgressIndicator()
				}
			}

			if (state.searchResults.isEmpty()) {
				EmptyResultUI()
			} else {

				HeadingTextUI(text = stringResource(Res.string.search_results))

				BookListUI(
					books = state.searchResults,
					scrollState = lazyStaggeredGridState,
					onClickBook = { onAction(BookListAction.OnClickBook(it)) }
				)
			}
		}
	}
}