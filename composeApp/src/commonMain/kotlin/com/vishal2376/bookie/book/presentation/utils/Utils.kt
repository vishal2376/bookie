package com.vishal2376.bookie.book.presentation.utils

import kotlin.math.pow
import kotlin.math.round

fun Double.roundTo(decimalPlaces: Int): Double {
	val factor = 10.0.pow(decimalPlaces.toDouble())
	return round(this * factor) / factor
}