package com.vishal2376.bookie.book.data.dto

import com.vishal2376.bookie.book.data.dto.serializer.BookWorkDtoSerializer
import kotlinx.serialization.Serializable

@Serializable(with = BookWorkDtoSerializer::class)
data class BookWorkDto(
	val description: String? = null,
)

@Serializable
data class DescriptionDto(
	val value: String
)