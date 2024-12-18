package com.vishal2376.bookie.core.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
	fun create(engine: HttpClientEngine): HttpClient {
		return HttpClient(engine) {
			install(ContentNegotiation) {
				json(Json {
					ignoreUnknownKeys = true
				})
			}
			install(HttpTimeout) {
				socketTimeoutMillis = 20_000
				requestTimeoutMillis = 20_000
			}
			install(Logging) {
				logger = object : Logger {
					override fun log(message: String) {
						println(message)
					}

				}
				level = LogLevel.ALL
			}
			defaultRequest {
				contentType(ContentType.Application.Json  )
			}
		}
	}
}