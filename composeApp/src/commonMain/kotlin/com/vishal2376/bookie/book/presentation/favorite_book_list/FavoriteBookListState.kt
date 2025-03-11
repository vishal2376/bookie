package com.vishal2376.bookie.book.presentation.favorite_book_list

import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.core.presentation.UiText

data class FavoriteBookListState(
	val favouriteBooks: List<Book> = emptyList(),
	val isLoading: Boolean = false,
	val errorMessage: UiText? = null
)
