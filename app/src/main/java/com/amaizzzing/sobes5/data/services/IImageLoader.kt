package com.amaizzzing.sobes5.data.services

interface IImageLoader<T> {
    fun loadInto(source: String, container: T)

    fun loadIntoWithRoundCorners(source: String, container: T, corner: Int)
}