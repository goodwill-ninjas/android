package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.prepareBottomMenu

@Composable
fun MyBottomBar(navController: NavController) {
    val bottomMenuItemsList = prepareBottomMenu()

    val contextForToast = LocalContext.current.applicationContext

    var selectedItem by remember {
        mutableStateOf("Home")
    }

    BottomAppBar(
        cutoutShape = CircleShape
    ) {
        bottomMenuItemsList.forEachIndexed { index, _ ->
            if (index == 1) {
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    icon = {},
                    enabled = false
                )
            }

            Box(modifier = Modifier.fillMaxSize()) {
                BottomNavigation(
                    modifier = Modifier.align(alignment = Alignment.BottomCenter)
                ) {

                    bottomMenuItemsList.forEach { menuItem ->

                        BottomNavigationItem(
                            selected = (selectedItem == menuItem.label),
                            onClick = {
                                selectedItem = menuItem.label
                                navController.navigate(menuItem.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = menuItem.icon,
                                    contentDescription = menuItem.label
                                )
                            },
                            label = {
                                Text(text = menuItem.label)
                            },
                            enabled = true
                        )
                    }
                }
            }
        }
    }
    Log.i("message", "this is FAB from BottomBar")
}