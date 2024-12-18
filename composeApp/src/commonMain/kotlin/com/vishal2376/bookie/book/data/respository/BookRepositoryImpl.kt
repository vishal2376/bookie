package com.vishal2376.bookie.book.data.respository

import com.vishal2376.bookie.book.data.mapper.toDomain
import com.vishal2376.bookie.book.data.remote.BookDataStore
import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.core.domain.DataError
import com.vishal2376.bookie.core.domain.Result
import com.vishal2376.bookie.core.domain.map

class BookRepositoryImpl(private val bookDataStore: BookDataStore) : BookRepository {
	override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
		return bookDataStore
			.searchBooks(query)
			.map { dto ->
				dto.results.map { it.toDomain() }
			}
	}

}