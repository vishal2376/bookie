package com.vishal2376.bookie.book.data.mapper

import com.vishal2376.bookie.book.data.database.BookEntity
import com.vishal2376.bookie.book.data.dto.SearchedBookDto
import com.vishal2376.bookie.book.domain.Book
import com.vishal2376.bookie.book.presentation.utils.roundTo

fun SearchedBookDto.toDomain(): Book {
	return Book(
		id = id.substringAfterLast("/"),
		title = title,
		imageUrl = if (coverKey != null) {
			"https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
		} else {
			"https://covers.openlibrary.org/b/olid/${coverAlternativeKey}-L.jpg"
		},
		authors = authorNames ?: emptyList(),
		description = null,
		language = languages ?: emptyList(),
		firstPublisher = firstPublishYear.toString(),
		averageRating = ratingsAverage?.roundTo(1),
		ratingCount = ratingsCount,
		numPages = numPagesMedian,
		numEditions = numEditions ?: 0

	)
}

fun Book.toBookEntity(): BookEntity {
	return BookEntity(
		id = id,
		title = title,
		imageUrl = imageUrl,
		authors = authors,
		description = description,
		language = language,
		firstPublisher = firstPublisher,
		averageRating = averageRating,
		ratingCount = ratingCount,
		numPages = numPages,
		numEditions = numEditions
	)
}

fun BookEntity.toDomain(): Book {
	return Book(
		id = id,
		title = title,
		imageUrl = imageUrl,
		authors = authors,
		description = description,
		language = language,
		firstPublisher = firstPublisher,
		averageRating = averageRating,
		ratingCount = ratingCount,
		numPages = numPages,
		numEditions = numEditions
	)
}