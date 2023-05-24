package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.bloodCenters

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BloodCenter(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    @ColumnInfo("name") val name: String? = "",
    @ColumnInfo("street_name") val streetName: String? = "",
    @ColumnInfo("street_number") val streetNumber: String? = "",
    @ColumnInfo("postal_code") val postalCode: String? = "",
    @ColumnInfo("city") val city: String? = "",
    @ColumnInfo("voivodeship") val voivodeship: String? = "",
    @ColumnInfo("geo_coordinates") val geoCoordinates: String? = "",
    @ColumnInfo("phone_number") val phoneNumber: String? = "",
    @ColumnInfo("open_from") val openFrom: String? = "",
    @ColumnInfo("open_to") val openTo: String? = "",
    @ColumnInfo("created_at") val createdAt: String? = ""
)