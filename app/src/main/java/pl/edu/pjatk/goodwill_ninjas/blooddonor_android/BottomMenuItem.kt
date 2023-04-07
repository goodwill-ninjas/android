package pl.edu.pjatk.goodwill_ninjas.blooddonor_android

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomMenuItem(val label: String, val icon: ImageVector)


public fun prepareBottomMenu(): List<BottomMenuItem> {

    val bottomMenuItemList = arrayListOf<BottomMenuItem>()

    bottomMenuItemList.add(BottomMenuItem(label = "Home", icon = Icons.Filled.Home))
    bottomMenuItemList.add(BottomMenuItem(label = "User", icon = Icons.Filled.Person))
    bottomMenuItemList.add(BottomMenuItem(label = "Donations", icon = Icons.Filled.CheckCircle))
    bottomMenuItemList.add(BottomMenuItem(label = "Tab", icon = Icons.Filled.Star))

    return bottomMenuItemList
}
