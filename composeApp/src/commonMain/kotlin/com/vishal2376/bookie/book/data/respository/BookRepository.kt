package com.vishal2376.bookie.book.data.respository

import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.core.domain.DataError
import com.vishal2376.bookie.core.domain.EmptyResult
import com.vishal2376.bookie.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
	suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
	suspend fun getBookDescription(id: String): Result<String?, DataError.Remote>

	fun getFavoriteBooks(): Flow<List<Book>>
	fun isBookFavorite(id: String): Flow<Boolean>
	suspend fun addFavoriteBook(book: Book): EmptyResult<DataError.Local>
	suspend fun deleteFavoriteBook(id: String)
}