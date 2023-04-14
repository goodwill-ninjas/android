package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetDialog(navController: NavController) {

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(

        sheetState = bottomSheetState,
        sheetContent = {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp))
                {
                    addDonation(navController)

                }
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    addDiscqualification(navController)
                }


            }
        },

        ) {
    }
    Log.i("mainpage", "this is BottomSheet")
}


@Composable
fun addDonation(navController: NavController) {
    Button(onClick = {
        navController.navigate(
            Screen.AddDonationFirstScreen.route
        )
    }) {
        Text(text = "Dodaj donację")
    }
}

@Composable
fun addDiscqualification(navController: NavController) {
    Button(onClick = {
        navController.navigate(
            Screen.AddDisqualification.route
        )
    }) {
        Text("Dodaj dyskwalifikację")
    }
}
