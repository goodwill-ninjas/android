package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.edu.pjatk.goodwill_ninjas.blooddonor_android.R

@Preview
@Composable
fun SignInScreen() {
    Column(
            modifier = Modifier
                    .fillMaxWidth(
                    )
                    .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Title()
        val localFocusManager = LocalFocusManager.current
        val emailState = remember { EmailState() }
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
        val passwordState = remember {
            PasswordState()
        }
        Password(passwordState.text, passwordState.error) {
            passwordState.text = it
            passwordState.validate()
        }
        SignInButton(enabled = emailState.isValid() && passwordState.isValid())
    }
}
@Composable
fun Title() {
    Text(text = "Zaloguj się")
}
@Composable
fun Email(email: String, error: String?, onEmailChanged: (String) -> Unit, onImeAction: () -> Unit) {
    Column {
        TextField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChange = { onEmailChanged(it) },
                label = { Text(text = "Email/Login") },
                colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next


                ),
                keyboardActions = KeyboardActions(
                        onNext = {

                            onImeAction()

                        }
                ),
                isError = error != null
        )
        error?.let { ErrorField(it) }
    }
}
@Composable
fun ErrorField(error: String) {
    Text(
            text = error,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(color = MaterialTheme.colors.error)
    )
}
@Composable
fun Password(password: String, error: String?, onPasswordChanged: (String) -> Unit) {

    val showPassword = remember {
        mutableStateOf(false)
    }
    TextField(
            value = password,
            onValueChange = {
                onPasswordChanged(it)
            },
            label = { Text(text = "Password") },
            colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (showPassword.value) {
                    IconButton(onClick = { showPassword.value = false }) {
                        Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = stringResource(R.string.hide_password)
                        )
                    }
                } else {
                    IconButton(onClick = { showPassword.value = true }) {
                        Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = stringResource(R.string.show_password)
                        )
                    }
                }
            },
            isError = error != null
    )
    error?.let { ErrorField(it) }
}
@Composable
fun SignInButton(enabled: Boolean) {
    Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            enabled = enabled,
    )
    {
        Text(text = "Kliknij aby się zalogować")
    }
}


