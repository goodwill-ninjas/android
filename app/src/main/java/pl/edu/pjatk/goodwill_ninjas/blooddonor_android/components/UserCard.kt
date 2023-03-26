package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import java.time.format.DateTimeFormatter


@Composable
fun UserCard(
    name: String,
    badgeLevel: Int,
    donatedBlood: Int,
) {
    Card (
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(R.drawable.droplet), contentDescription = "avatar", Modifier.height(40.dp))
            Column {
                Text(name, fontWeight = FontWeight(700))
                Text(text = "Odznaka honorowego krwiodawcy ${badgeLevel} stopnia.")
            }
        }
    }
}