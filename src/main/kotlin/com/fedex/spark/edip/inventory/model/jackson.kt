package com.fedex.spark.edip.inventory.model

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.io.IOException
import java.text.SimpleDateFormat


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

class MoneyDeserializer @JvmOverloads constructor(vc: Class<*>? = null) :
    StdDeserializer<Money?>(vc) {

    override fun deserialize(p0: JsonParser, p1: DeserializationContext?): Money {
        val str = p0.getText();
        return Money(value = str.toInt())
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
