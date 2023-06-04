package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import android.content.Context
import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.api.user.UserResponse
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.mainPageBadge.MainPageBadge

@Composable
fun UserCard(
    userData: UserResponse,
    context: Context
) {
    Card (
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
//            MainPageBadge(featRanksNumber = 0)
            Column {
                userData.username?.let { Text(it, fontWeight = FontWeight(700), fontSize = 36.sp) }
                val currentExp: Float = ((userData.exp_details?.min_experience?.let {
                    userData.exp_details.current_experience?.minus(
                        it
                    )
                })?.toFloat() ?: 0.0) as Float
                val currentMaxExp: Float = ((userData.exp_details?.min_experience?.let {
                    userData.exp_details.max_experience?.minus(
                        it
                    )
                })?.toFloat() ?: 0.1) as Float
                Text(text = "Aktualny poziom: ${userData.exp_details?.level}")
                Text(text = "Doświadczenie:")
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = userData.exp_details?.min_experience.toString())
                    Text(text = userData.exp_details?.current_experience.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = userData.exp_details?.max_experience.toString())
                }
                LinearProgressIndicator(
                    progress = currentExp/currentMaxExp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}