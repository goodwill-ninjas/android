package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.Donation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationDao

@Database(
    entities = [Donation::class],
    version = 2,
    exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun donationDao(): DonationDao

    companion object {
        // Singleton - for making sure only one database is open
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2 = object: Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Donation RENAME COLUMN donation_date TO created_at ADD COLUMN (`companion_user_id` INTEGER, `blood_pressure` TEXT, `hemoglobin` REAL, `details` TEXT, `deleted_at` INTEGER)")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                instance
            }
        }
    }
}