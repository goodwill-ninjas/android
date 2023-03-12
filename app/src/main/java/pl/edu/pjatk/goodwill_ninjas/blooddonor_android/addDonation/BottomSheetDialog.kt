package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetDialog() {
    val contextForToast = LocalContext.current.applicationContext

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {


            ListItem(
                modifier = Modifier.clickable {


                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }
                },

                text = {
                    Text(
                        text = stringResource(id = R.string.dodaj_donację),


                        )

                },

                icon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite"
                    )
                }
            )


        },
        sheetState = bottomSheetState
    ) {
        // app UI
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    bottomSheetState.show()
                }
            }) {
                Text(text = "Dziennik donacji")
            }
        }
    }
}

@Preview()
@Composable
fun BottomSheetPreview() {

    Surface {

        BottomSheetDialog()


    }

}