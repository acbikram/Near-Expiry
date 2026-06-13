package com.nearexpiry.manager.data.local.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Central place for all Room migrations. Add one object per version bump
 * and register it in [ExpiryDatabase.getInstance] via `.addMigrations(...)`.
 *
 * Keeping these explicit (rather than relying solely on
 * fallbackToDestructiveMigration) means existing users' scan history
 * survives schema changes.
 */

/**
 * v1 -> v2: adds a composite index on (barcode, expiryDate).
 *
 * This index backs [com.nearexpiry.manager.data.local.dao.ExpiryItemDao.findByBarcodeAndExpiry],
 * which is called on every scan to detect duplicates. Without it, that
 * lookup is a full table scan that gets slower as the inventory grows.
 *
 * The index name matches Room's auto-generated convention
 * (`index_<table>_<col1>_<col2>`) for the `indices` entry added to
 * [com.nearexpiry.manager.data.local.entity.ExpiryItemEntity].
 */
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_expiry_items_barcode_expiryDate` " +
                "ON `expiry_items` (`barcode`, `expiryDate`)"
        )
    }
}

/** All migrations the database currently supports, in order. */
val ALL_MIGRATIONS: Array<Migration> = arrayOf(MIGRATION_1_2)
