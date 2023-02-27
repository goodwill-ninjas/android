package pl.edu.pjatk.goodwill_ninjas.blooddonor_android


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.Navigation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Navigation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.ui.theme.BlooddonorandroidTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlooddonorandroidTheme {

                Navigation()

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
