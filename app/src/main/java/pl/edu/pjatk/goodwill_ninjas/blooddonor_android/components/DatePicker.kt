package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
) {
    var datePickerState = rememberDatePickerState()
    androidx.compose.material3.DatePicker(
        datePickerState = datePickerState,
        modifier = Modifier.padding(16.dp)
    )
    Text(
        text = "${
            if (datePickerState.selectedDateMillis != null)
                Date(datePickerState.selectedDateMillis!!)
            else
                "No selection"
        }"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {
    var datePickerState = remember { mutableStateOf(value) }
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Wybierz datę",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                DatePicker()
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            if (datePickerState.value.isEmpty()) {
                                datePickerState.value = "Pole daty nie może być puste"
                            }
                            setValue(datePickerState.value)
                            setShowDialog(false)
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    ) {
                        Text(text = "Wybrano")
                    }
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDateDialog() {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value)
        CustomDialog(value = "", setShowDialog = {
            showDialog.value = it
        }) {
        }
        Box(modifier = Modifier.background(Color.White)) {
            Column(
                modifier = Modifier
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = {
                    showDialog.value = true
                }) {
                    Text(text = "Kalendarz")
                }
            }
        }
    }



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomDateDialog()
}
