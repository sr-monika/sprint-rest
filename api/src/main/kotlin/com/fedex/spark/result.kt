package com.shoprunner.cosmos

import arrow.core.flatMap

fun <A> A.success(): Result<A> = Result.success(this)

fun <A> Throwable.failure(): Result<A> = Result.failure(this)

fun <A> String.error(): Result<A> = Result.failure(RuntimeException(this))

fun <A> Result<A?>.notnull(f: () -> Throwable): Result<A> =
    this.fold(
        { it?.success() ?: Result.failure(f()) }

    ) { Result.failure(it) }

fun <A, RES> Result.Companion.mapN(a: Result<A>, fn: (A) -> RES): Result<RES> {
    return a.map(fn)
}

private fun getFailure(results: List<Result<Any?>>): Result<Any?>? {
    return results.find { it.isFailure }
}

@Suppress("UNCHECKED_CAST")
fun <A, B, RES> Result.Companion.mapN(a: Result<A>, b: Result<B>, fn: (A, B) -> RES): Result<RES> {
    return getFailure(listOf(a, b)) as Result<RES>? ?: success(fn(a.getOrThrow(), b.getOrThrow()))
}

@Suppress("UNCHECKED_CAST")
fun <A, B, C, RES> Result.Companion.mapN(a: Result<A>, b: Result<B>, c: Result<C>, fn: (A, B, C) -> RES): Result<RES> {
    return getFailure(listOf(a, b, c)) as Result<RES>? ?: success(fn(a.getOrThrow(), b.getOrThrow(), c.getOrThrow()))
}

@Suppress("UNCHECKED_CAST")
fun <A, B, C, D, RES> Result.Companion.mapN(
    a: Result<A>,
    b: Result<B>,
    c: Result<C>,
    d: Result<D>,
    fn: (A, B, C, D) -> RES
): Result<RES> {
    return getFailure(listOf(a, b, c, d)) as Result<RES>? ?: success(fn(a.getOrThrow(), b.getOrThrow(), c.getOrThrow(), d.getOrThrow()))
}

@Suppress("UNCHECKED_CAST")
fun <A, B, C, D, E, RES> Result.Companion.mapN(
    a: Result<A>,
    b: Result<B>,
    c: Result<C>,
    d: Result<D>,
    e: Result<E>,
    fn: (A, B, C, D, E) -> RES
): Result<RES> {
    return getFailure(listOf(a, b, c, d, e)) as Result<RES>? ?: success(fn(a.getOrThrow(), b.getOrThrow(), c.getOrThrow(), d.getOrThrow(), e.getOrThrow()))
}

fun <A, RES> Result.Companion.flatMapN(a: Result<A>, fn: (A) -> Result<RES>): Result<RES> {
    return a.flatMap(fn)
}

@Suppress("UNCHECKED_CAST")
fun <A, B, RES> Result.Companion.flatMapN(a: Result<A>, b: Result<B>, fn: (A, B) -> Result<RES>): Result<RES> {
    return getFailure(listOf(a, b)) as Result<RES>? ?: fn(a.getOrThrow(), b.getOrThrow())
}

@Suppress("UNCHECKED_CAST")
fun <A, B, C, RES> Result.Companion.flatMapN(a: Result<A>, b: Result<B>, c: Result<C>, fn: (A, B, C) -> Result<RES>): Result<RES> {
    return getFailure(listOf(a, b, c)) as Result<RES>? ?: fn(a.getOrThrow(), b.getOrThrow(), c.getOrThrow())
}

@Suppress("UNCHECKED_CAST")
fun <A, B, C, D, RES> Result.Companion.flatMapN(
    a: Result<A>,
    b: Result<B>,
    c: Result<C>,
    d: Result<D>,
    fn: (A, B, C, D) -> Result<RES>
): Result<RES> {
    return getFailure(listOf(a, b, c, d)) as Result<RES>? ?: fn(a.getOrThrow(), b.getOrThrow(), c.getOrThrow(), d.getOrThrow())
}
