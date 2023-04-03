package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.content.Context
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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


//@Preview()
//@Composable
//fun BottomSheetPreview() {
//
//    Surface {
//
//        BottomSheetDialog()
//
//
//    }
//
//}