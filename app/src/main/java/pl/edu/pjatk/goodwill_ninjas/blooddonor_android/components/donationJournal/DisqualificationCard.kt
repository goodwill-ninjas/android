package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.donationJournal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DisqualificationCard(
    disqualificationDate: Int,
    days: Int
) {
    Box(modifier = Modifier.fillMaxWidth()){
        Card(
            elevation = 5.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Row {
                    Icon(
                        Icons.Rounded.Error,
                        contentDescription = null,
                        Modifier.height(50.dp)
                    )

                }
            }
        }
    }
}