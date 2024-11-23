package com.vishal2376.bookie.book.presentation.book_list

import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.core.presentation.UiText

data class BookListState(
	val searchQuery: String = "Kotlin",
	val searchResults: List<Book> = emptyList(),
	val favouriteBooks: List<Book> = emptyList(),
	val isLoading: Boolean = false,
	val errorMessage: UiText? = null
)
