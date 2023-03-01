package com.fedex.spark.edip.inventory.model

enum class SetOrAdjust {
    SET, ADJUST, FAILURE
}

class Money private constructor(set: Int) {
    val value: Int = set

    companion object {
        fun create(value: Double) = Result.success(Money((value * 100).toInt()))
    }
}

class UtcTimestamp private constructor(set: Long) {
    val value: Long = set
    companion object {
        fun create(value: Long) =
            if (value >= 0)
                Result.success(UtcTimestamp(value))
            else
                Result.failure(javax.validation.ValidationException("Invalid UtcTimestamp $value"))
    }
}


class Sku private constructor(set: String) {
    val value = set

    companion object {
        fun create(value: String) =
            if (!value.isBlank())
                Result.success(Sku(value))
            else
                Result.failure(javax.validation.ValidationException("Sku may not be empty"))
    }
}


class Quantity private constructor(set: Int) {
    val value: Int = set
    companion object {
        fun create(value: Int) = Result.success(Quantity(value))
    }
}
