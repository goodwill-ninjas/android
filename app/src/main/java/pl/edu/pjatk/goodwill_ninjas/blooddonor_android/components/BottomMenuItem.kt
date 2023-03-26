package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Routes
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen

data class BottomMenuItem(val label: String, val icon: ImageVector, val route: String)


public fun prepareBottomMenu(): List<BottomMenuItem> {

    val bottomMenuItemList = arrayListOf<BottomMenuItem>()

    bottomMenuItemList.add(BottomMenuItem(label = "Home", icon = Icons.Filled.Home, route = Routes.SELF))
    bottomMenuItemList.add(BottomMenuItem(label = "User", icon = Icons.Filled.Person, route = Routes.JOURNAL))
    bottomMenuItemList.add(BottomMenuItem(label = "Donations", icon = Icons.Filled.CheckCircle, route = Routes.JOURNAL))
    bottomMenuItemList.add(BottomMenuItem(label = "Tab", icon = Icons.Filled.Star, route = Routes.SELF))

    return bottomMenuItemList
}