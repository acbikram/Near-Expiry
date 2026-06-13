package com.nearexpiry.manager.domain.model

data class ExpiryItem(
    val id: Long,
    val barcode: String,
    val expiryDate: String,
    val quantity: Double,
    val createdAt: Long,
    val updatedAt: Long
)
