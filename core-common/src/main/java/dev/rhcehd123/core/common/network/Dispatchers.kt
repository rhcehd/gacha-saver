package dev.rhcehd123.core.common.network

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Dispatcher(val dispatcher: Dispatchers)

enum class Dispatchers {
    IO,
    Default,
}