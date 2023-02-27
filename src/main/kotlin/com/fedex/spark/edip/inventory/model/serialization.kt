package com.fedex.spark.edip.inventory.model

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer

class InventoryIdSerializer : ToStringSerializer() {
    override fun serialize(p0: Any?, p1: JsonGenerator?, p2: SerializerProvider?) {
        val obj = p0 as InventoryId
        super.serialize( obj.id, p1,  p2)
    }
}

@JsonSerialize(using = InventoryIdSerializer::class)
interface InventoryId {
    val id: Int
}

class IntValueSerializer : ToStringSerializer() {
    override fun serialize(p0: Any?, p1: JsonGenerator?, p2: SerializerProvider?) {
        val obj = p0 as IntValue
        super.serialize(obj.value, p1, p2)
    }
}
@JsonSerialize(using = IntValueSerializer::class)
interface IntValue {
    val value: Int
}


class LongValueSerializer : ToStringSerializer() {
    override fun serialize(p0: Any?, p1: JsonGenerator?, p2: SerializerProvider?) {
        val obj = p0 as LongValue
        super.serialize(obj.value, p1, p2)
    }
}
@JsonSerialize(using = LongValueSerializer::class)
interface LongValue {
    val value: Long
}

class StringValueSerializer : ToStringSerializer() {
    override fun serialize(p0: Any?, p1: JsonGenerator?, p2: SerializerProvider?) {
        val obj = p0 as StringValue
        super.serialize(obj.value, p1, p2)
    }
}
@JsonSerialize(using = StringValueSerializer::class)
interface StringValue {
    val value: String
}
