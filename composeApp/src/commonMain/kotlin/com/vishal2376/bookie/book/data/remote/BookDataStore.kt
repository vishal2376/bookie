package com.vishal2376.bookie.book.data.remote

import com.vishal2376.bookie.book.data.dto.BookWorkDto
import com.vishal2376.bookie.book.data.dto.SearchResponseDto
import com.vishal2376.bookie.core.domain.DataError
import com.vishal2376.bookie.core.domain.Result

interface BookDataStore {
	suspend fun searchBooks(
		query: String,
		resultLimit: Int? = null
	): Result<SearchResponseDto, DataError.Remote>

	suspend fun getBookDetails(id: String): Result<BookWorkDto, DataError.Remote>
}