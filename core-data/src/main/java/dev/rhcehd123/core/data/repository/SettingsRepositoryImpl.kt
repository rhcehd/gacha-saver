package dev.rhcehd123.core.data.repository

import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor() : SettingsRepository {
    override val settingsData: SettingsData = SettingsData(1)
}