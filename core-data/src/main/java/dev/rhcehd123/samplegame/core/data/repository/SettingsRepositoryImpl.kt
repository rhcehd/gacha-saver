package dev.rhcehd123.samplegame.core.data.repository

import dev.rhcehd123.samplegame.core.data.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor() : SettingsRepository {
    override val settingsData: SettingsData = SettingsData(1)
}