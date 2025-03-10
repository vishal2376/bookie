package com.vishal2376.bookie.book.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {

	@Upsert
	suspend fun upsertBook(bookEntity: BookEntity)

	@Query("SELECT * FROM bookentity")
	fun getAllBooks(): Flow<List<BookEntity>>

	@Query("SELECT * FROM bookentity WHERE id = :id")
	suspend fun getBookById(id: String): BookEntity?

	@Query("DELETE FROM bookentity WHERE id = :id")
	suspend fun deleteBookById( id: String)

}