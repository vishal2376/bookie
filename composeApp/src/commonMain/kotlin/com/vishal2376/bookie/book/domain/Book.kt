package com.vishal2376.bookie.book.domain

data class Book(
	val id: String,
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
