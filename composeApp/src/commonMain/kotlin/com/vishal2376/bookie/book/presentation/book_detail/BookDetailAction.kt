package com.vishal2376.bookie.book.presentation.book_detail

sealed interface BookDetailAction {
	data object OnClickBack : BookDetailAction
	data object OnClickFavorite : BookDetailAction
}