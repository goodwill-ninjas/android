package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Preview
@Composable
fun SignInScreen(){
    Column(
        modifier = Modifier
            .fillMaxWidth(
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Title()
        Email()
        Password()
        SignInButton()
    }
}
@Composable
fun SignInButton(){
    
    Button(onClick = { /*TODO*/ },
    modifier=Modifier.fillMaxWidth(),
        ) {
        Text(text = "Kliknij aby się zalogować")
    }
}
@Composable
fun Password(){
    val passwordState = remember {
        mutableStateOf(TextFieldValue())
    }
    TextField(value = passwordState.value, onValueChange = {passwordState.value = it},
        label = { Text(text = "Password")})
}
@Composable
fun Email(){
    var emailState = remember {
        mutableStateOf(TextFieldValue())
    }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = emailState.value, onValueChange = {emailState.value = it} ,
            label = { Text(text = "Email/Login")}
        )
}

@Composable
fun Title(){
    Text(text = "Zaloguj się")
}

