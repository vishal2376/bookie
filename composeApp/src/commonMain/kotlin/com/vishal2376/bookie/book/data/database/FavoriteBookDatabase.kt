package com.vishal2376.bookie.book.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vishal2376.bookie.book.data.database.converters.StringTypeConverter

@Database(
	entities = [BookEntity::class],
	version = 1
)
@TypeConverters(StringTypeConverter::class)
abstract class FavoriteBookDatabase : RoomDatabase() {
	abstract val favoriteBookDao: FavoriteBookDao

	companion object {
		const val DATABASE_NAME = "favorite_books_db"
	}
}