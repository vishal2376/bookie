package com.vishal2376.bookie.book.presentation.book_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookListViewModel : ViewModel() {
	private val _state = MutableStateFlow(BookListState())
	val state = _state.asStateFlow()

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
}