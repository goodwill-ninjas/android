package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.bloodCenters.BloodCenter
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.bloodCenters.BloodCenterDao
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.Disqualification
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.disqualification.DisqualificationDAO
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.Donation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationDao
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat.UserFeat
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat.UserFeatDao


@Database(
    entities = [Donation::class, UserFeat::class, BloodCenter::class, Disqualification::class],
    version = 8,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun donationDao(): DonationDao
    abstract fun userFeatDao(): UserFeatDao
    abstract fun bloodCenterDao(): BloodCenterDao
    abstract fun disqualificationDao(): DisqualificationDAO
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Donation RENAME COLUMN donation_date TO created_at ADD COLUMN (`companion_user_id` INTEGER, `blood_pressure` TEXT, `hemoglobin` REAL, `details` TEXT, `deleted_at` INTEGER)")
            }
        }
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Donation ADD COLUMN (`hand` TEXT, `blood_center` TEXT)")
            }
        }
        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE userFeat(`id` INTEGER NOT NULL PRIMARY KEY, `userId` INTEGER, `featId` TEXT, `featName` TEXT, `featDescription` TEXT, `achievedRanks` TEXT, `nextRanks` TEXT)")
            }
        }
        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE bloodCenter(`id` INTEGER NOT NULL PRIMARY KEY, `name` TEXT, `street_name` TEXT, `postal_code` TEXT, `city` TEXT, `voivodeship` TEXT, `geo_coordinates` TEXT, `phone_number` TEXT, `open_from` TEXT, `open_to` TEXT, `created_at` TEXT)")
            }
        }

        private val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE bloodCenter ADD COLUMN `street_number` TEXT")
            }
        }
        private val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE disqualification(`id` INTEGER NOT NULL PRIMARY KEY, `date_start` INTEGER, `date_finish` INTEGER, `blood_pressure` TEXT, `hemoglobin` REAL, `details` TEXT)")
            }
        }
        private val MIGRATION_7_8 = object : Migration(7, 8) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE disqualification RENAME COLUMN date_finish TO days")
            }
        }
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).addMigrations(
                    MIGRATION_1_2,
                    MIGRATION_2_3,
                    MIGRATION_3_4,
                    MIGRATION_4_5,
                    MIGRATION_5_6,
                    MIGRATION_6_7,
                    MIGRATION_7_8
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


