package com.vishal2376.bookie.book.data.database


import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory() {
	actual fun createDatabase(): RoomDatabase.Builder<FavoriteBookDatabase> {
		val os = System.getProperty("os.name").lowercase()
		val homeDir = System.getProperty("user.home")

		val appDir = when {
			os.contains("win") -> {
				File(System.getenv("APPDATA"), "Bookie")
			}

			os.contains("mac") -> {
				File(homeDir, "Library/Application Support/Bookie")
			}

			else -> {
				File(homeDir, ".config/Bookie")
			}
		}
		val dbFile = File(appDir, FavoriteBookDatabase.DATABASE_NAME)
		return Room.databaseBuilder(name = dbFile.absolutePath)
	}
}