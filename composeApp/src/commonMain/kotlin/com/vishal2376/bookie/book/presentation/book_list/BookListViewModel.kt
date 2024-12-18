package com.vishal2376.bookie.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal2376.bookie.book.data.respository.BookRepository
import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.core.domain.onError
import com.vishal2376.bookie.core.domain.onSuccess
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(private val repository: BookRepository) : ViewModel() {
	private var searchJob: Job? = null
	private val cachedBooks: List<Book> = emptyList()

	private val _state = MutableStateFlow(BookListState())
	val state = _state
		.onStart {
			if (cachedBooks.isEmpty()) {
				observeSearchQuery()
			}
		}
		.stateIn(
			viewModelScope,
			SharingStarted.WhileSubscribed(5000),
			_state.value
		)


	fun onAction(action: BookListAction) {
		when (action) {
			is BookListAction.OnClickBook -> {

			}

			is BookListAction.OnSearchQueryChange -> {
				_state.update {
					it.copy(searchQuery = action.query)
				}
			}
		}
	}

	fun observeSearchQuery() {
		state.map { it.searchQuery }
			.distinctUntilChanged()
			.debounce(500L)
			.onEach { query ->
				when {
					query.isBlank() -> {
						_state.update {
							it.copy(
								errorMessage = null,
								searchResults = emptyList()
							)
						}
					}

					query.length >= 2 -> {
						searchJob?.cancel()
						searchJob = searchBooks(query)
					}
				}
			}
			.launchIn(viewModelScope)
	}

	private fun searchBooks(query: String) = viewModelScope.launch {
		_state.update { it.copy(isLoading = true) }

		repository.searchBooks(query)
			.onSuccess { books ->
				_state.update {
					it.copy(
						isLoading = false,
						errorMessage = null,
						searchResults = books
					)
				}
			}.onError {
				_state.update {
					it.copy(
						isLoading = false,
						errorMessage = it.errorMessage,
						searchResults = emptyList()
					)
				}
			}
	}
}