package com.vishal2376.bookie.book.presentation.book_detail

import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.core.presentation.UiText

data class BookDetailState(
	val book: Book? = null,
	val isFavorite: Boolean = false,
	val isLoading: Boolean = false,
	val errorMessage: UiText? = null
)
