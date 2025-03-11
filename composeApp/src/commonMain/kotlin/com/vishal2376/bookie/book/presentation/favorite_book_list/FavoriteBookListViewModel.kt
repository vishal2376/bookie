package com.vishal2376.bookie.book.presentation.favorite_book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal2376.bookie.book.data.respository.BookRepository
import com.vishal2376.bookie.book.domain.Book
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class FavoriteBookListViewModel(private val repository: BookRepository) : ViewModel() {
	private val cachedBooks: List<Book> = emptyList()
	private var observeFavoriteBooksJob: Job? = null

	private val _state = MutableStateFlow(FavoriteBookListState())
	val state = _state
		.onStart {
			observeFavoriteBooks()
		}
		.stateIn(
			viewModelScope,
			SharingStarted.WhileSubscribed(5000),
			_state.value
		)

	private fun observeFavoriteBooks() {
		observeFavoriteBooksJob?.cancel()
		observeFavoriteBooksJob = repository.getFavoriteBooks()
			.onEach { books ->
				_state.value = _state.value.copy(
					favouriteBooks = books
				)
			}.launchIn(viewModelScope)
	}
}