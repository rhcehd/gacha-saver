package dev.rhcehd123.samplegame.core.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import dev.rhcehd123.samplegame.core.datastore.data.GachaTable
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class GachaDataSerializer @Inject constructor() : Serializer<GachaTable> {
    override val defaultValue: GachaTable = GachaTable.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): GachaTable {
        return try {
            GachaTable.parseFrom(input)
        } catch (e: IOException) {
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override suspend fun writeTo(t: GachaTable, output: OutputStream) {
        t.writeTo(output)
    }
}
