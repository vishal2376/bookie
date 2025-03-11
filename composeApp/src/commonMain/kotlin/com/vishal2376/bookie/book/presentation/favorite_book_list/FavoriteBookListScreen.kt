package com.vishal2376.bookie.book.presentation.favorite_book_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import bookie.composeapp.generated.resources.Res
import bookie.composeapp.generated.resources.favorite_books
import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.book.presentation.book_list.components.EmptyResultUI
import com.vishal2376.bookie.book.presentation.components.BookListUI
import com.vishal2376.bookie.book.presentation.components.HeadingTextUI
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteBookListScreenRoot(
	viewModel: FavoriteBookListViewModel = koinViewModel(),
	onBookClick: (Book) -> Unit,
	onClickBack: () -> Unit
) {
	val state by viewModel.state.collectAsStateWithLifecycle()
	FavoriteBookListScreen(
		state = state,
		onAction = { action ->
			when (action) {
				is FavoriteBookListAction.OnClickBook -> onBookClick(action.book)
				FavoriteBookListAction.OnClickBack -> onClickBack()
			}
		}
	)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoriteBookListScreen(
	state: FavoriteBookListState,
	onAction: (FavoriteBookListAction) -> Unit
) {

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
						text = "Back",
						style = MaterialTheme.typography.titleLarge,
					)
				},
				navigationIcon = {
					IconButton(onClick = {
						onAction(FavoriteBookListAction.OnClickBack)
					}) {
						Icon(
							imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
							tint = MaterialTheme.colorScheme.primary,
							contentDescription = null
						)
					}
				})
		},
	) { innerPadding ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding),
		) {

			if (state.isLoading) {
				Box(
					modifier = Modifier.fillMaxSize(),
					contentAlignment = Alignment.Center
				) {
					CircularProgressIndicator()
				}
			}

			if (state.favouriteBooks.isEmpty()) {
				EmptyResultUI()
			} else {

				HeadingTextUI(text = stringResource(Res.string.favorite_books))

				BookListUI(
					books = state.favouriteBooks,
					scrollState = lazyStaggeredGridState,
					onClickBook = { onAction(FavoriteBookListAction.OnClickBook(it)) }
				)
			}
		}
	}
}