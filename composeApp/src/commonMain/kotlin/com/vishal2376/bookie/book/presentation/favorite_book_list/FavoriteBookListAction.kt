package com.vishal2376.bookie.book.presentation.favorite_book_list

import com.vishal2376.bookie.book.domain.Book

sealed class FavoriteBookListAction {
	data object OnClickBack : FavoriteBookListAction()
	data class OnClickBook(val book: Book) : FavoriteBookListAction()
}