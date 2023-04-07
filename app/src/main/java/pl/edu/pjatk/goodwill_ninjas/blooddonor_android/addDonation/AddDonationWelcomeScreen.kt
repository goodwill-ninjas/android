package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.addDonation

import android.view.Surface
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DateFormat
import java.time.LocalDate
import java.util.Calendar



@Composable

fun WelcomeScreen() {
    val paddingModifier = Modifier.padding(10.dp)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .width(200.dp)
                .height(70.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = paddingModifier
            ) {
                Text(
                    text = "Krew pełna", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                    modifier = paddingModifier
                )
            }
        }
        Surface(
            modifier = Modifier
                .width(200.dp)
                .height(60.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = paddingModifier
            ) {
                Column() {
                    GetDate()


                }
            }
        }

        Surface(
            modifier = Modifier
                .width(300.dp)
                .height(100.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = paddingModifier
            ) {
                Column() {
                    Text(
                        text = "Możesz oddać krew pełną już za: ",

                        modifier = paddingModifier
                    )
                    Row {
//                        GetDate()
                        PeriodInDays()
                    }


                }
            }
        }
    }

}


@Composable
fun GetDate() {
    val calendar = Calendar.getInstance().time
    val dateFormat = DateFormat.getDateInstance().format(calendar)
    Text(text = "$dateFormat")

}

@Composable
fun PeriodInDays(){
val daysRemaining = 50;
    Text(text = "$daysRemaining")


}

@Preview()
@Composable
fun WelcomeScreenPreview() {

    Surface {

        WelcomeScreen()


    }

}