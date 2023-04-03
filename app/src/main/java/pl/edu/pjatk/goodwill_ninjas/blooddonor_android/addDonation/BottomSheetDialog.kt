package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetDialog() {

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)

    ModalBottomSheetLayout(

        sheetState = bottomSheetState,
        sheetContent = {
            Column(modifier = Modifier.padding(12.dp)) {
                addDonation()
                addDiscqualification()
            }
        },

        ) {
    }
}

@Composable
fun addDonation(navController: NavController) {
    Button(onClick = {
        navController.navigate(
            Screen.BottomSheetDialog.route
        )
    }) {
        androidx.compose.material3.Text(text = "Dodaj donację")
    }
}

@Composable
fun addDiscqualification() {
    Button(onClick = {
        //your onclick code here
    }) {
        Text(text = stringResource(id = R.string.add_disqualification))
    }
}
