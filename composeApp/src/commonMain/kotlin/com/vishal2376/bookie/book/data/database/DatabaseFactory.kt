package com.vishal2376.bookie.book.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
	fun createDatabase(): RoomDatabase.Builder<FavoriteBookDatabase>
}