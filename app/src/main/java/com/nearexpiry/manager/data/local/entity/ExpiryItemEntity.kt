package com.nearexpiry.manager.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nearexpiry.manager.domain.model.ExpiryItem
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = "expiry_items",
    indices = [Index(value = ["barcode", "expiryDate"])]
)
data class ExpiryItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val barcode: String,
    val expiryDate: String,
    val quantity: Int,
    val createdAt: Long,
    val updatedAt: Long
)

fun ExpiryItemEntity.toDomain() = ExpiryItem(
    id = id,
    barcode = barcode,
    expiryDate = expiryDate,
    quantity = quantity,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun ExpiryItem.toEntity() = ExpiryItemEntity(
    id = id,
    barcode = barcode,
    expiryDate = expiryDate,
    quantity = quantity,
    createdAt = createdAt,
    updatedAt = updatedAt
)
