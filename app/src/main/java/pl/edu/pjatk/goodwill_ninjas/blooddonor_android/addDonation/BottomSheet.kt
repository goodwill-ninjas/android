package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainBottomScreen(navController: NavController) {
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
        sheetContent = { BottomSheet(navController) },
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
fun BottomSheet(navController: NavController) {
    Column(
        modifier = Modifier.padding(32.dp)
    ) {

        Row {
            addDonation1(navController)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            addDiscqualification1(navController)
        }
    }
}
@Composable
@Preview
fun see() {
    MainBottomScreen(navController = NavController(LocalContext.current))
}
@Composable
fun addDonation1(navController: NavController) {
    Button(onClick = {
        navController.navigate(
            Screen.AddDonationFirstScreen.route
        )
    }) {
        Text(text = "Dodaj donację")
    }
}
@Composable
fun addDiscqualification1(navController: NavController) {
    Button(onClick = {
        navController.navigate(
            Screen.AddDisqualification.route
        )
    }) {
        Text("Dodaj dyskwalifikację")
    }
}