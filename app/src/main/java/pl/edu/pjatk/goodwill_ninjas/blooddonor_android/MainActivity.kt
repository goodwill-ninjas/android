package pl.edu.pjatk.goodwill_ninjas.blooddonor_android


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.room.Room
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.datepicker.DatePickerService
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.AppDatabase
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Navigation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.ui.theme.BlooddonorandroidTheme
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.donation.DonationViewModel

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }
    private val viewModel by viewModels<DonationViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DonationViewModel(db.donationDao()) as T
                }
            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlooddonorandroidTheme {
                val state by viewModel.state.collectAsState()
                Navigation(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BlooddonorandroidTheme {
        Navigation
    }
}