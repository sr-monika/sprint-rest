package com.fedex.spark.edip.inventory

import java.time.Instant


fun nowTs() : Long {
    val instant = Instant.now()
    return (instant.toEpochMilli() * 1000.toLong() + instant.nano )
}