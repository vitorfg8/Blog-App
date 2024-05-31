package com.github.vitorfg8.blogapp.domain.usecase

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class GetCurrentFormattedTimeUseCase {
    operator fun invoke(): String {
        val currentDateTime = Instant.now()
        return currentDateTime.atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)
    }
}