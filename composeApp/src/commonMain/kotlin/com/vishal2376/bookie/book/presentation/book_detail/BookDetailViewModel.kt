package com.vishal2376.bookie.book.presentation.book_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.vishal2376.bookie.app.Route
import com.vishal2376.bookie.book.data.respository.BookRepository
import com.vishal2376.bookie.core.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
	private val bookRepository: BookRepository,
	private val savedStateHandle: SavedStateHandle
) : ViewModel() {

	private val bookId = savedStateHandle.toRoute<Route.BookDetail>().id

	private val _state = MutableStateFlow(BookDetailState())
	val state = _state.onStart {
		fetchBookDescription()
		observeFavoriteStatus()
	}.stateIn(
		scope = viewModelScope,
		started = SharingStarted.WhileSubscribed(5000),
		initialValue = _state.value
	)

	fun onAction(action: BookDetailAction) {
		when (action) {
			is BookDetailAction.OnSelectedBookChanged -> {
				_state.update { it.copy(book = action.book) }
			}

			BookDetailAction.OnClickBack -> Unit

			BookDetailAction.OnClickFavorite -> {
				viewModelScope.launch {
					if (state.value.isFavorite) {
						bookRepository.deleteFavoriteBook(bookId)
					} else {
						state.value.book?.let { book ->
							bookRepository.addFavoriteBook(book)
						}
					}
				}
			}
		}
	}

	private fun observeFavoriteStatus() {
		bookRepository.isBookFavorite(bookId)
			.onEach { isFavorite ->
				_state.update { it.copy(isFavorite = isFavorite) }
			}
			.launchIn(viewModelScope)
	}

	private fun fetchBookDescription() {
		viewModelScope.launch {
			bookRepository.getBookDescription(bookId)
				.onSuccess { description ->
					_state.update {
						it.copy(
							book = it.book?.copy(description = description),
							isLoading = false
						)
					}
				}
		}
	}

}