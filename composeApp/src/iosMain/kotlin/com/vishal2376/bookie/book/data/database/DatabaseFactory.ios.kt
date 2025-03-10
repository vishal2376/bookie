@file:OptIn(ExperimentalForeignApi::class)

package com.vishal2376.bookie.book.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class DatabaseFactory {
	actual fun createDatabase(): RoomDatabase.Builder<FavoriteBookDatabase> {
		val dbFile = documentDirectory() + "/${FavoriteBookDatabase.DATABASE_NAME}"
		return Room.databaseBuilder<FavoriteBookDatabase>(name = dbFile)
	}

	private fun documentDirectory(): String {
		val documentsDirectory = NSFileManager.defaultManager.URLForDirectory(
			directory = NSDocumentDirectory,
			inDomain = NSUserDomainMask,
			appropriateForURL = null,
			create = false,
			error = null,
		)
		return requireNotNull(documentsDirectory?.path())
	}
}