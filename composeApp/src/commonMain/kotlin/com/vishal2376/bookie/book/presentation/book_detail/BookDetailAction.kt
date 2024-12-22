package com.vishal2376.bookie.book.presentation.book_detail

import com.vishal2376.bookie.book.domain.Book

sealed interface BookDetailAction {
	data object OnClickBack : BookDetailAction
	data object OnClickFavorite : BookDetailAction
	data class OnSelectedBookChanged(val book: Book) : BookDetailAction
}