package com.vishal2376.bookie.book.presentation.book_list

import com.vishal2376.bookie.book.domain.Book

sealed class BookListAction {
	data class OnSearchQueryChange(val query: String) : BookListAction()
	data class OnClickBook(val book: Book) : BookListAction()
}