package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R

@Composable
fun ParseCapacityToIcons(capacity: String) {
    if (capacity == "STOP") {
        Row {
            for (i in 1..4) {
                Icon(
                    painter = painterResource(id = R.drawable.droplet),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .height(20.dp)
                )
            }
        }
    }
    if (capacity == "ALMOST_FULL") {
        Row {
            for (i in 1..3) {
                Icon(
                    painter = painterResource(id = R.drawable.droplet),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .height(20.dp)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.droplet),
                tint = Color.Black,
                contentDescription = null,
                modifier = Modifier
                    .height(20.dp)
            )
        }
    }
    if (capacity == "OPTIMAL") {
        Row {
            for (i in 1..2) {
                Icon(
                    painter = painterResource(id = R.drawable.droplet),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier
                        .height(20.dp)
                )
            }
            for (i in 1..2) {
                Icon(
                    painter = painterResource(id = R.drawable.droplet),
                    tint = Color.Black,
                    contentDescription = null,
                    modifier = Modifier
                        .height(20.dp)
                )
            }
        }
    }
    if (capacity == "MODERATE") {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.droplet),
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier
                    .height(20.dp)
            )
            for (i in 1..3) {
                Icon(
                    painter = painterResource(id = R.drawable.droplet),
                    tint = Color.Black,
                    contentDescription = null,
                    modifier = Modifier
                        .height(20.dp)
                )
            }
        }
    }
    if (capacity == "CRITICAL") {
        Row {
            for (i in 1..4) {
                Icon(
                    painter = painterResource(id = R.drawable.droplet),
                    tint = Color.Black,
                    contentDescription = null,
                    modifier = Modifier
                        .height(20.dp)
                )
            }
        }
    }
}