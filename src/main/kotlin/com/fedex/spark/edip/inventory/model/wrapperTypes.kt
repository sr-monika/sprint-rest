package com.fedex.spark.edip.inventory.model

data class Money(val value: Int) {
    companion object {
        fun create(value: Double) = Result.success(Money((value * 100).toInt()))
    }
}

data class UtcTimestamp(val value: Long) {
    companion object {
        fun create(value: Long) =
            if (value >= 0)
                Result.success(UtcTimestamp(value))
            else
                Result.failure(javax.validation.ValidationException("Invalid UtcTimestamp $value"))
    }
}

data class Sku(val value: String) {
    companion object {
        fun create(value: String) =
            if (!value.isBlank())
                Result.success(Sku(value))
            else
                Result.failure(javax.validation.ValidationException("Sku may not be empty"))
    }
}


data class Quantity(val value: Int) {
    companion object {
        fun create(value: Int) = Result.success(Quantity(value))
    }
}
