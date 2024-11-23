package com.vishal2376.bookie

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform