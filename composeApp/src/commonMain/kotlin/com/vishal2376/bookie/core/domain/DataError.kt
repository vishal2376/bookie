package com.vishal2376.bookie.core.domain

sealed interface DataError : Error {
	enum class Remote : DataError {
		REQUEST_TIMEOUT,
		TOO_MANY_REQUESTS,
		NO_INTERNET,
		SERVER,
		SERIALIZATION,
		UNKNOWN
	}

	enum class Local : DataError {
		NO_DATA,
		DISK_FULL,
		UNKNOWN
	}
}