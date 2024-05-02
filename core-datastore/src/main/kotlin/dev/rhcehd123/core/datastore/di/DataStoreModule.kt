package dev.rhcehd123.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.rhcehd123.core.common.network.Dispatcher
import dev.rhcehd123.core.common.network.Dispatchers.IO
import dev.rhcehd123.core.common.network.di.ApplicationScope
import dev.rhcehd123.core.datastore.GachaDataSerializer
import dev.rhcehd123.samplegame.core.datastore.data.GachaTable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideGachaDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        gachaDataSerializer: GachaDataSerializer,
    ): DataStore<GachaTable> =
        DataStoreFactory.create(
            serializer = gachaDataSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
            migrations = listOf(),
        ) {
            context.dataStoreFile("gacha_table.pb")
        }

}