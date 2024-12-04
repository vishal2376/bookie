package com.vishal2376.bookie.book.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bookie.composeapp.generated.resources.Res
import bookie.composeapp.generated.resources.app_name
import bookie.composeapp.generated.resources.search_results
import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.book.presentation.book_list.components.BookSearchBar
import com.vishal2376.bookie.book.presentation.book_list.components.EmptyResultUI
import com.vishal2376.bookie.book.presentation.components.BookItemUI
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
				searchQuery = state.searchQuery,
				onSearchQueryChange = { onAction(BookListAction.OnSearchQueryChange(it)) },
				onSearch = { keyboardController?.hide() }
			)

			if (state.searchResults.isEmpty()) {
				EmptyResultUI()
			} else {

				Row(
					modifier = Modifier.padding(24.dp, 16.dp),
					horizontalArrangement = Arrangement.spacedBy(8.dp),
					verticalAlignment = Alignment.CenterVertically
				) {
					Box(
						modifier = Modifier
							.width(6.dp)
							.height(20.dp)
							.background(MaterialTheme.colorScheme.primary)
					)
					Text(
						text = stringResource(Res.string.search_results),
						style = MaterialTheme.typography.titleMedium,
						fontWeight = FontWeight.Bold,
					)
				}

				LazyVerticalStaggeredGrid(
					columns = StaggeredGridCells.FixedSize(160.dp),
					verticalItemSpacing = 24.dp,
					horizontalArrangement = Arrangement.spacedBy(
						24.dp,
						Alignment.CenterHorizontally
					),
					content = {
						items(state.searchResults) {
							BookItemUI(book = it)
						}
					})
			}
		}
	}
}