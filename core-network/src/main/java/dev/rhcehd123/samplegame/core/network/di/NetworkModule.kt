package dev.rhcehd123.samplegame.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rhcehd123.samplegame.core.network.GachaSaverNetworkDataSource
import dev.rhcehd123.samplegame.core.network.retrofit.RetrofitGachaSaverNetwork

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {
    @Binds
    abstract fun provideGachaSaverNetworkDataSource(
        retrofitGachaSaverNetwork: RetrofitGachaSaverNetwork
    ): GachaSaverNetworkDataSource
}