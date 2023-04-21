package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Routes
data class BottomMenuItem(val label: String, val icon: ImageVector, val route: String)
fun prepareBottomMenu(): List<BottomMenuItem> {
    val bottomMenuItemList = arrayListOf<BottomMenuItem>()
    bottomMenuItemList.add(BottomMenuItem(label = "Panel", icon = Icons.Filled.Home, route = Routes.SELF))
    bottomMenuItemList.add(BottomMenuItem(label = "Donacje", icon = Icons.Filled.WaterDrop, route = Routes.JOURNAL))
    bottomMenuItemList.add(BottomMenuItem(label = "Placówki", icon = Icons.Filled.Map, route = Routes.SELF))
    bottomMenuItemList.add(BottomMenuItem(label = "Profil", icon = Icons.Filled.Person, route = Routes.SELF))
    return bottomMenuItemList
}