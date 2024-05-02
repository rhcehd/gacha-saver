package dev.rhcehd123.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rhcehd123.core.data.repository.GachaDataRepository
import dev.rhcehd123.core.data.repository.GachaDataRepositoryImpl
import dev.rhcehd123.core.data.repository.GachaSaverRepository
import dev.rhcehd123.core.data.repository.GachaSaverRepositoryImpl
import dev.rhcehd123.core.data.repository.SettingsRepository
import dev.rhcehd123.core.data.repository.SettingsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    abstract fun bindsGachaDataRepository(
        gachaDataRepository: GachaDataRepositoryImpl
    ): GachaDataRepository

    @Binds
    abstract fun bindsGachaSaverRepository(
        gachaSaverRepository: GachaSaverRepositoryImpl
    ): GachaSaverRepository

    @Binds
    abstract fun bindsSettingsRepository(
        settingsRepository: SettingsRepositoryImpl
    ): SettingsRepository
}