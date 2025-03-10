package com.vishal2376.bookie.book.data.remote

import com.vishal2376.bookie.book.data.dto.BookWorkDto
import com.vishal2376.bookie.book.data.dto.SearchResponseDto
import com.vishal2376.bookie.core.data.safeCall
import com.vishal2376.bookie.core.domain.DataError
import com.vishal2376.bookie.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://openlibrary.org"

class BookDataSourceImpl(
	private val httpClient: HttpClient
) : BookDataStore {
	override suspend fun searchBooks(
		query: String,
		resultLimit: Int?
	): Result<SearchResponseDto, DataError.Remote> {
		return safeCall<SearchResponseDto> {
			httpClient.get(urlString = "$BASE_URL/search.json") {
				parameter("q", query)
				parameter("limit", resultLimit)
				parameter("language", "eng")
				parameter(
					"fields",
					"key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count"
				)
			}
		}
	}

	override suspend fun getBookDetails(id: String): Result<BookWorkDto, DataError.Remote> {
		return safeCall<BookWorkDto> {
			httpClient.get(urlString = "$BASE_URL/works/$id.json")
		}
	}
}