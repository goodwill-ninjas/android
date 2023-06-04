package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.badgeCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.database.userFeat.UserFeat

@Composable
fun BadgeCard(
    userFeat: UserFeat
) {
    Card(
        modifier = Modifier.padding(5.dp),
        elevation = 5.dp
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(R.drawable.odznaka_1), contentDescription = null, modifier = Modifier.width(30.dp).padding(2.dp))
            Column {
                userFeat.featName?.let { Text(text = it, fontWeight = FontWeight.Bold, fontSize = 20.sp) }
                userFeat.achievedRanks?.let {Text(text = "Zdobyte rangi: ${it.count{it.isDigit()}}")}
                userFeat.nextRanks?.let { Text(text = "Do zdobycia: ${it.count{it.isDigit()}}")}
                userFeat.featDescription?.let { Text(text = it) }
            }
        }
    }
}