package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddAlert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign

@Composable
fun MytopBar(name: String) {

    val contextForToast = LocalContext.current.applicationContext
    TopAppBar(title = {
        Text(
            text = if (name.isNotEmpty()) "Witaj $name" else "Nie jesteś zalogowany",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
    },
        actions = {

            TopAppBarActionButton(
                imageVector = Icons.Outlined.AddAlert,
                description = "Alert"
            ) {
                Toast.makeText(contextForToast, "Alert", Toast.LENGTH_SHORT)
                    .show()
            }


            TopAppBarActionButton(
                imageVector = Icons.Outlined.Share,
                description = "Share"
            ) {
                Toast.makeText(contextForToast, "Share", Toast.LENGTH_SHORT)
                    .show()
            }

            TopAppBarActionButton(
                imageVector = Icons.Outlined.Search,
                description = "Search"
            ) {
                Toast.makeText(contextForToast, "Search", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    )
}

@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(imageVector = imageVector, contentDescription = description)
    }
}