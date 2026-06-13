package com.nearexpiry.manager.utils

import com.nearexpiry.manager.data.local.entity.ExpiryItemEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class BackupData(
    val version: Int = 1,
    val items: List<ExpiryItemEntity>
)

object JsonBackup {
    private val json = Json { prettyPrint = true }

    fun exportToJson(outputStream: OutputStream, items: List<ExpiryItemEntity>) {
        val backup = BackupData(items = items)
        outputStream.bufferedWriter().use {
            it.write(json.encodeToString(backup))
        }
    }

    fun importFromJson(inputStream: InputStream): List<ExpiryItemEntity> {
        val backup = inputStream.bufferedReader().use {
            json.decodeFromString<BackupData>(it.readText())
        }
        return backup.items
    }
}
