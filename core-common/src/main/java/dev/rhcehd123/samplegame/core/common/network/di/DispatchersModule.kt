package dev.rhcehd123.samplegame.core.common.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rhcehd123.samplegame.core.common.network.Dispatcher
import dev.rhcehd123.samplegame.core.common.network.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Dispatcher(Dispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO

    @Provides
    @Dispatcher(Dispatchers.Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Default
}