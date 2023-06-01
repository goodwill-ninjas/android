package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database

import androidx.sqlite.db.SupportSQLiteDatabase


open class Migration(x: Int, y: Int) {
    open fun migrate(database: SupportSQLiteDatabase){}
}