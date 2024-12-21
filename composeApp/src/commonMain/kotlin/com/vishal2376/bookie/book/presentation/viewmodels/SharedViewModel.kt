package com.vishal2376.bookie.book.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.vishal2376.bookie.book.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {
	private val _selectedBook = MutableStateFlow<Book?>(null)
	val selectedBook = _selectedBook.asStateFlow()

	fun onSelectBook(book: Book?) {
		_selectedBook.value = book
	}
}