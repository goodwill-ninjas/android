package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetDialog(navController: NavController) {

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)

    ModalBottomSheetLayout(

        sheetState = bottomSheetState,
        sheetContent = {
            Column(modifier = Modifier.padding(12.dp)) {
                addDonation(navController)
                addDiscqualification(navController)
            }
        },

        ) {
    }
}
@Composable
fun addDonation(navController: NavController) {
    Button(onClick = {
        navController.navigate(
            Screen.AddDonationFirstScreen.route
        )
    }) {
        androidx.compose.material3.Text(text = "Dodaj donację")
    }
}
@Composable
fun addDiscqualification(navController: NavController) {
    Button(onClick = {
        navController.navigate(
            Screen.AddDisqualification.route)
    }) {
        Text(text = stringResource(id = R.string.add_disqualification))
    }
}
