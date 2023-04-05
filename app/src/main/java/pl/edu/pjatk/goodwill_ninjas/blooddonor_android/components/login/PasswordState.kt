package pl.edu.pjatk.goodwill_ninjas.blooddonor_android.components.login

class PasswordState : TextFieldState(
    validator = ::isPasswordValid,
    errorMessage = { passwordErrorMessage() }
) {}
    fun isPasswordValid(password: String) =
        password.length >= 4
    fun passwordErrorMessage() = "Hasło jest nieprawidłowe."
