package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components

import android.os.Build.VERSION_CODES.R
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.DatePicker
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Routes.ADD_DONATION
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import androidx.compose.ui.res.painterResource


@Composable
fun ScaffoldAndFAB(name: String, navController: NavController, route: String, stringRes: String) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = { MytopBar(name="Wojciech") },
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MyBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(

                    route
                )
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },

        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
//        val image = painterResource(id=R.drawable.droplet)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding()) {
//                Image(painter = image, contentDescription = null, Modifier.height(250.dp))
            }
            Box(modifier = Modifier.padding()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .clickable { },
                    elevation = 10.dp
                ) {
                    Column(
                        modifier = Modifier.padding(15.dp)
                    ) {
                        Text(text = stringRes)

                    }

                    Text(
                        text = stringRes
                    )

                }
            }
        }
    }
    Log.i("message", "this is FAB")
}



