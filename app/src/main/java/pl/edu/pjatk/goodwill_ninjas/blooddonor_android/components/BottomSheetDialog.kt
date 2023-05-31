package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetDialog(navController: NavController) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()
    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = { BottomSheetOne(navController) },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        else sheetState.show()
                    }
                }
            ) {
                Text(text = "Wybierz akcję")
            }
        }
    }
}
@Composable
fun BottomSheetOne(navController: NavController) {
    Column(
        modifier = Modifier.padding(32.dp)
    ) {

        Row {
            addDonation(navController)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            addDiscqualification(navController)
        }
    }
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