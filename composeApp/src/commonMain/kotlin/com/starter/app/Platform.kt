package com.starter.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform