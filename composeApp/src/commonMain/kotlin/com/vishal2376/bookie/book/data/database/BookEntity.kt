package com.vishal2376.bookie.book.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
	@PrimaryKey(autoGenerate = false) val id: String,
	val title: String,
	val imageUrl: String,
	val authors: List<String>,
	val description: String?,
	val language: List<String>?,
	val firstPublisher: String?,
	val averageRating: Double?,
	val ratingCount: Int?,
	val numPages: Int?,
	val numEditions: Int,
)