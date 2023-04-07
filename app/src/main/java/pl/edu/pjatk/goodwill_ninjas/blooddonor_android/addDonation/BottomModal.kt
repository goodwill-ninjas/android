import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation.addDiscqualification1
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation.addDonation1
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.ui.theme.BlooddonorandroidTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomModal(navController: NavController) {

    BlooddonorandroidTheme() {

        val coroutineScope = rememberCoroutineScope()
        val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded)
        ModalBottomSheetLayout(
            sheetState = bottomState,
            sheetContent = {
                Spacer(modifier = Modifier.height(1.dp))
                addDonation1(navController)
                addDiscqualification1(navController)
            }
        ) {
            Scaffold(

                bottomBar = {
                    BottomAppBar(modifier = Modifier) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch { bottomState.show() }
                            }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.droplet),
                                contentDescription = "aa"
                            )
                        }
                    }
                },

                content = { innerPadding ->{Unit}

                }
            )
        }
//                }
    }
}
