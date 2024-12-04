package com.vishal2376.bookie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.vishal2376.bookie.book.presentation.book_list.BookListScreenRoot
import com.vishal2376.bookie.book.presentation.book_list.BookListViewModel
import com.vishal2376.bookie.core.presentation.theme.BookieTheme

@Composable
fun App(darkTheme: Boolean, dynamicColor: Boolean) {
	BookieTheme(darkTheme, dynamicColor) {
		BookListScreenRoot(viewModel = remember { BookListViewModel() }, onBookClick = {})
	}
}