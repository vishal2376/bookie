package com.vishal2376.bookie.book.data.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object StringTypeConverter {
	@TypeConverter
	fun toString(list: List<String>): String {
		return Json.encodeToString(list)
	}

	@TypeConverter
	fun fromString(value: String): List<String> {
		return Json.decodeFromString(value)
	}
}