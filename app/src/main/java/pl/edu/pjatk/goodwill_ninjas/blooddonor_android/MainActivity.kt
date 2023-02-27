package pl.edu.pjatk.goodwill_ninjas.blooddonor_android

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material.ScaffoldState

import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.ui.theme.BlooddonorandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlooddonorandroidTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {

                        MyApp()
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BlooddonorandroidTheme {
        MyApp()
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,

        topBar = { MytopBar() },
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MyBottomBar() },
        floatingActionButton = {
            FloatingActionButton(onClick = {

            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        },

        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}

@Composable
fun MytopBar() {

    val contextForToast = LocalContext.current.applicationContext
    TopAppBar(title = {
        Text(
            text = "Witaj Adam",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center

        )
    },
        actions = {

            TopAppBarActionButton(
                imageVector = Icons.Outlined.Email,
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

@Composable
fun MyBottomBar() {
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
                                Toast.makeText(
                                    contextForToast,
                                    menuItem.label, Toast.LENGTH_SHORT
                                ).show()
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
}
