package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.ui.theme.BlooddonorandroidTheme




@Composable
fun MyBottomBar(navController: NavController) {
    val bottomMenuItemsList = prepareBottomMenu()
    var isVisible by rememberSaveable  {
        mutableStateOf(true)
    }


    val contextForToast = LocalContext.current.applicationContext

    var selectedItem by remember {
        mutableStateOf("Home")
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),

        ) {
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

}