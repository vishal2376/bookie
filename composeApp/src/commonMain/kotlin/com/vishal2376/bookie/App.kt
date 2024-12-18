package com.vishal2376.bookie

import androidx.compose.runtime.Composable
import com.vishal2376.bookie.book.presentation.book_list.BookListScreenRoot
import com.vishal2376.bookie.book.presentation.book_list.BookListViewModel
import com.vishal2376.bookie.core.presentation.theme.BookieTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(darkTheme: Boolean, dynamicColor: Boolean) {
	val viewModel = koinViewModel<BookListViewModel>()

	BookieTheme(darkTheme, dynamicColor) {
		BookListScreenRoot(viewModel = viewModel, onBookClick = {})
	}
}