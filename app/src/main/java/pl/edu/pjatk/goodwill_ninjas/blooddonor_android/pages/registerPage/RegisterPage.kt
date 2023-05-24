package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.pages.registerPage

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login.Email
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login.EmailState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login.Password
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login.PasswordState
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.register.BloodTypePicker
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.register.GenderPicker
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.register.UserName
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.navigation.Screen
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.LoginViewModel
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.viewmodels.login.RegistrationEvent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SignUpScreen(navController: NavController, context: Context) {
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
            Text(text = "Zarejestruj się")
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
        loginViewModel.onEvent(RegistrationEvent.SetEmail(emailState.text))
        Row {
            Password(passwordState.text, passwordState.error) {
                passwordState.text = it
                passwordState.validate()
            }
        }
        loginViewModel.onEvent(RegistrationEvent.SetPassword(passwordState.text))
        Row {
            UserName(loginViewModel)
        }
        Row {
            GenderPicker(loginViewModel)
        }
        Row {
            BloodTypePicker(loginViewModel)
        }
        Column {
            Button(onClick = {
                val state = loginViewModel.registrationDataStore.value
                loginViewModel.register(state)
                navController.navigate(Screen.MainPage.route)
            },
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                enabled = emailState.isValid() && passwordState.isValid()
            ) {
                Text(text = "Zarejestruj się")
            }
        }
    }
}
