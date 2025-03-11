package com.vishal2376.bookie.app

import kotlinx.serialization.Serializable

sealed interface Route {
	@Serializable
	data object BookGraph : Route

	@Serializable
	data object BookList : Route

	@Serializable
	data object FavoriteBookList : Route

	@Serializable
	data class BookDetail(val id: String) : Route
}