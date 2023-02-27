package com.fedex.spark.edip.inventory.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = MoneyDeserializer::class)
data class Money(override val value: Int) : IntValue

data class UtcTimestamp(override val value: Long) : LongValue

data class Sku(override val value: String) : StringValue

data class Quantity(override val value: Int) : IntValue
