package com.fedex.spark.edip.inventory.model

import org.springframework.beans.factory.annotation.Value
import javax.validation.constraints.Size

data class Money(val value: Int);

data class UtcTimestamp(val utc: Long)

data class Sku(val value: String)

data class Quantity(val value: Int)
