package com.vishal2376.bookie.book.data.mapper

import com.vishal2376.bookie.book.data.dto.SearchedBookDto
import com.vishal2376.bookie.book.domain.Book

fun SearchedBookDto.toDomain(): Book {
	return Book(
		id = id,
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
		averageRating = ratingsAverage,
		ratingCount = ratingsCount,
		numPages = numPagesMedian,
		numEditions = numEditions ?: 0

	)
}