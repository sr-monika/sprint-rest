package com.fedex.spark.edip.inventory.service

interface  DataOutlet<T> {
    fun inform(data: T) : Result<T?>
}