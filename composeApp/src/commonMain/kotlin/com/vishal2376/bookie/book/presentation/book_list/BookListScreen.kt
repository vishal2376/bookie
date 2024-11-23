package com.vishal2376.bookie.book.presentation.book_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vishal2376.bookie.book.domain.Book
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

@Composable
private fun BookListScreen(
	state: BookListState,
	onAction: (BookListAction) -> Unit
) {

}