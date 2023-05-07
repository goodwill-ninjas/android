package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationDao
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.Donation

@Database(
    entities = [Donation::class],
    version = 3,
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
        private val MIGRATION_2_3 = object: Migration(2,3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE ADD COLUMN (`hand` TEXT, `blood_center` TEXT)")
            }
        }
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()
                INSTANCE = instance
                instance
            }

        }
    }
}


