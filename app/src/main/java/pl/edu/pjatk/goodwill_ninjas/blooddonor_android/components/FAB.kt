package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen.AddDisqualification.route


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyFAB(navController: NavController) {
    Scaffold(
    floatingActionButtonPosition = FabPosition.Center,
    isFloatingActionButtonDocked = true,

        floatingActionButton = {
            FloatingActionButton(


                onClick = {
                    navController.navigate(
                        route
                    )
                },
            )
            {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ){}
}



