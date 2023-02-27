package com.shoprunner.cosmos.datatypes

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.fedex.spark.edip.inventory.model.Money
import com.fedex.spark.edip.inventory.model.Quantity
import com.fedex.spark.edip.inventory.model.Sku
import com.fedex.spark.edip.inventory.model.UtcTimestamp
import org.springframework.stereotype.Service
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty1

@Service
object JacksonModule : SimpleModule() {

    private inline fun <reified T> serialize(f: KProperty1<T, String>) {
        addSerializer(object : StdSerializer<T>(T::class.java) {
            override fun serialize(value: T?, gen: JsonGenerator, serializers: SerializerProvider) {
                if (value == null) gen.writeNull() else gen.writeString(f(value))
            }
        })
    }

    private inline fun <reified T> serializeLong(f: KProperty1<T, Long>) {
        addSerializer(object : StdSerializer<T>(T::class.java) {
            override fun serialize(value: T?, gen: JsonGenerator, serializers: SerializerProvider) {
                if (value == null) gen.writeNull() else gen.writeNumber(f(value))
            }
        })
    }

    private inline fun <reified T> serializeInt(f: KProperty1<T, Int>) {
        addSerializer(object : StdSerializer<T>(T::class.java) {
            override fun serialize(value: T?, gen: JsonGenerator, serializers: SerializerProvider) {
                if (value == null) gen.writeNull() else gen.writeNumber(f(value))
            }
        })
    }

    inline fun <reified T> serializeDouble(f: KProperty1<T, Double>) {
        addSerializer(object : StdSerializer<T>(T::class.java) {
            override fun serialize(value: T?, gen: JsonGenerator, serializers: SerializerProvider) {
                if (value == null) gen.writeNull() else gen.writeNumber(f(value))
            }
        })
    }

    inline fun <reified T> serializeBoolean(f: KProperty1<T, Boolean>) {
        addSerializer(object : StdSerializer<T>(T::class.java) {
            override fun serialize(value: T?, gen: JsonGenerator, serializers: SerializerProvider) {
                if (value == null) gen.writeNull() else gen.writeBoolean(f(value))
            }
        })
    }

    private inline fun <reified T> deserialize(f: KFunction1<String, T>) {
        addDeserializer(
            T::class.java,
            object : JsonDeserializer<T>() {
                override fun deserialize(p: JsonParser, ctxt: DeserializationContext): T? =
                    p.valueAsString?.let { f.invoke(it) }
            }
        )
    }

    private inline fun <reified T> deserializeInt(f: KFunction1<Int, T>) {
        addDeserializer(
            T::class.java,
            object : JsonDeserializer<T>() {
                override fun deserialize(p: JsonParser, ctxt: DeserializationContext): T? = f(p.intValue)
            }
        )
    }

    private inline fun <reified T> deserializeLong(f: KFunction1<Long, T>) {
        addDeserializer(
            T::class.java,
            object : JsonDeserializer<T>() {
                override fun deserialize(p: JsonParser, ctxt: DeserializationContext): T? =
                    if (p.valueAsString == null) null else f.invoke(p.longValue)
            }
        )
    }

    private inline fun <reified T> deserializeDouble(f: KFunction1<Double, T>) {
        addDeserializer(
            T::class.java,
            object : JsonDeserializer<T>() {
                override fun deserialize(p: JsonParser, ctxt: DeserializationContext): T? =
                    if (p.valueAsString == null) null else f.invoke(p.doubleValue)
            }
        )
    }

    private inline fun <reified T> deserializeBoolean(f: KFunction1<Boolean, T>) {
        addDeserializer(
            T::class.java,
            object : JsonDeserializer<T>() {
                override fun deserialize(p: JsonParser, ctxt: DeserializationContext): T? =
                    if (p.valueAsString == null) null else f.invoke(p.booleanValue)
            }
        )
    }

    init {
        this.serializeInt(Money::value)
        this.serializeInt(Quantity::value)
        this.serializeLong(UtcTimestamp::utc)
        this.serialize(Sku::value)

        deserializeInt(::Money)
        deserializeInt(::Quantity)
        deserializeLong(::UtcTimestamp)
        deserialize(::Sku)
    }
}
