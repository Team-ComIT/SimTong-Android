package com.comit.local.extension

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

internal fun LocalDateTime.toEpochSecond() =
    this.atZone(ZoneId.systemDefault()).toEpochSecond()

internal fun Long.epochSecondToLocalDateTime() = Instant
    .ofEpochSecond(this)
    .atZone(ZoneId.systemDefault())
    .toLocalDateTime()
