package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import java.util.*


@Composable
fun DatePicker(
) {
    var date by rememberSaveable { mutableStateOf("") }
    val day: Int
    val month: Int
    val year: Int
    val mCalendar = Calendar.getInstance()
    day = mCalendar.get(Calendar.DAY_OF_MONTH)
    month = mCalendar.get(Calendar.MONTH)
    year = mCalendar.get(Calendar.YEAR)
    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, day: Int, month: Int, year: Int -> date = "$year/${month+1})/$day" },
        day,
        month,
        year
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.align(Alignment.Center)) {
            OutlinedTextField(value = (date), onValueChange = { date = it },
                readOnly = true,
                label = { Text(text = "Data")}

            )
            Icon(
                imageVector = Icons.Filled.DateRange, contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(4.dp)
                    .clickable {
                        mDatePickerDialog.show()
                    }
            )
        }
    }
}


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
                            fontWeight = Bold
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
            OutlinedButton(
                onClick = {
                    showDialog.value = true
                },
                modifier = Modifier.width(200.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Red
                ),
                shape = RoundedCornerShape(5),

                ) {
                Text(
                    text = "Kalendarz",
                    fontWeight = Bold, color = Color.Red
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DatePicker()
}