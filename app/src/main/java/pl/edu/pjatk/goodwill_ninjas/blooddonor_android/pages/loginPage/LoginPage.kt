package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.loginPage

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login.Email
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login.EmailState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login.Password
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login.PasswordState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Routes
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.utils.ErrorField
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel

@Composable
fun SignInScreen(navController: NavController, context: Context) {
    val emailState = remember { EmailState() }
    val passwordState = remember { PasswordState() }
    val loginViewModel = LoginViewModel(context)
    Column(
        modifier = Modifier
            .fillMaxWidth(
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row {
            Text(text = "Zaloguj się")
        }
        Row {
            val localFocusManager = LocalFocusManager.current
            Email(
                emailState.text, emailState.error,
                onEmailChanged = {
                    emailState.text = it
                    emailState.validate()
                },
                onImeAction = {
                    localFocusManager.moveFocus(FocusDirection.Down)
                }
            )
        }
        Row {
            Password(passwordState.text, passwordState.error) {
                passwordState.text = it
                passwordState.validate()
            }
        }
        Column {
            Button(onClick = {
                    loginViewModel.login(emailState.text, passwordState.text)
                    navController.navigate(Screen.MainPage.route)
                             },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                enabled = emailState.isValid() && passwordState.isValid()
                ) {
                Text(text = "Zaloguj się")
            }
        }
    }
}
