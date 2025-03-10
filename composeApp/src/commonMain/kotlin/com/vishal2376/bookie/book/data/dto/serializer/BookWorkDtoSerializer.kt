package com.vishal2376.bookie.book.data.dto.serializer

import com.vishal2376.bookie.book.data.dto.BookWorkDto
import com.vishal2376.bookie.book.data.dto.DescriptionDto
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

object BookWorkDtoSerializer : KSerializer<BookWorkDto> {
	override val descriptor: SerialDescriptor =
		buildClassSerialDescriptor(BookWorkDto::class.simpleName!!) {
			element<String?>("description ")
		}

	override fun deserialize(decoder: Decoder): BookWorkDto =
		decoder.decodeStructure(descriptor) {
			var description: String? = null
			while (true) {
				when (val index = decodeElementIndex(descriptor)) {
					0 -> {
						val jsonDecoder =
							decoder as? JsonDecoder ?: error("Can be called only by JSON")
						val element = jsonDecoder.decodeJsonElement()
						description = if (element is JsonObject) {
							decoder.json.decodeFromJsonElement(
								DescriptionDto.serializer(),
								element
							).value
						} else if (element is JsonPrimitive && element.isString) {
							element.content
						} else {
							null
						}
					}

					CompositeDecoder.DECODE_DONE -> break
					else -> error("Unexpected index: $index")
				}
			}
			return@decodeStructure BookWorkDto(description)
		}

	override fun serialize(encoder: Encoder, value: BookWorkDto) =
		encoder.encodeStructure(descriptor) {
			value.description?.let {
				encodeStringElement(descriptor, 0, it)
			}
		}
}