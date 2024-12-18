package com.vishal2376.bookie.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
	@SerialName("docs") val results: List<SearchedBookDto>,
)