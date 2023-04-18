package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.DonationDao
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.donation.Donation

@Database(entities = [Donation::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun donationDao(): DonationDao

    companion object {
        // Singleton - for making sure only one database is open
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}