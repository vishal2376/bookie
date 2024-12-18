package com.vishal2376.bookie.book.data.respository

import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.core.domain.DataError
import com.vishal2376.bookie.core.domain.Result

interface BookRepository {
	suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}