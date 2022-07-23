package com.example.pets_project.ui.screens.login.model

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pets_project.repository.Repository
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.services.network.models.UserLoginData
import com.example.pets_project.services.network.models.UserRegistrationData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val networkService: NetworkService,
    private val repository: Repository
) :
    ViewModel(), EventHandler<LoginEvent> {

    private val _viewState = MutableLiveData(LoginViewState())
    val viewState: LiveData<LoginViewState> = _viewState

    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.SignInClicked -> signActionState(LoginSubState.Login)
            LoginEvent.SignUpClicked -> signActionState(LoginSubState.Registration)
            LoginEvent.ForgotButtonClicked -> TODO()
            LoginEvent.LoginButtonClicked -> checkLoginAction()
            LoginEvent.RegistrationButtonClicked -> checkRegistrationAction()
            LoginEvent.SignWOLoginClicked -> TODO()

            is LoginEvent.EmailChanged -> emailChanged(event.value)
            is LoginEvent.NameChanged -> nameChanged(event.value)
            is LoginEvent.PassChanged -> passChanged(event.value)
            is LoginEvent.PassConfirmationChanged -> passConfirmationChanged(event.value)
        }
    }

    private fun emailChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(emailValue = value))
    }
    private fun passChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(passwordValue = value))
    }
    private fun passConfirmationChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(passwordConfirmationValue = value))
    }
    private fun nameChanged(value: String) {
        _viewState.postValue(_viewState.value?.copy(nameValue = value))
    }

    private fun refreshErrorMessages(): LoginViewState {

        return _viewState.value?.copy(
            nameTextErrorState = EditTextErrorState.None,
            emailTextErrorState = EditTextErrorState.None,
            passTextErrorState = EditTextErrorState.None,
            passConfirmationTextErrorState = EditTextErrorState.None
        )!!
    }
    private fun signActionState(loginSubState: LoginSubState) {

        var vl = refreshErrorMessages()

        vl = vl.copy(
            passwordValue = "",
            passwordConfirmationValue = "",
            loginSubState = loginSubState
        )

        _viewState.postValue(vl)
    }

    private fun signIn() {
        viewModelScope.launch {
            val token = networkService.login(
                UserLoginData(
                    viewState.value!!.emailValue,
                    viewState.value!!.passwordValue
                )
            )

            when (token) {
                null -> {
                    var vl = refreshErrorMessages()
                    vl = vl.copy(passTextErrorState = EditTextErrorState.IsNotValid)
                    _viewState.postValue(vl)

                    Log.e("error", "неверный логин или пароль")
                }
                else -> repository.saveToken(token.accessToken, token.refreshToken)
            }
        }
    }

    private fun checkLoginAction() {

        var vl = refreshErrorMessages()
        val email: String = viewState.value?.emailValue!!
        var isFieldError = false

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            vl = vl.copy(emailTextErrorState = EditTextErrorState.IsNotRegex)
            isFieldError = true
        }

        if (viewState.value?.passwordValue == "") {
            vl = vl.copy(passTextErrorState = EditTextErrorState.IsEmpty)
            isFieldError = true
        }

        vl = vl.copy(passwordValue = "")

        _viewState.postValue(vl)

        if (!isFieldError) {

            signIn()
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            val token = networkService.registration(
                UserRegistrationData(
                    viewState.value!!.emailValue,
                    viewState.value!!.passwordValue,
                    viewState.value!!.emailValue
                )
            )
            when (token) {
                null -> {
                    var vl = refreshErrorMessages()
                    vl = vl.copy(emailTextErrorState = EditTextErrorState.IsNotValid)
                    _viewState.postValue(vl)
                }
                else -> repository.saveToken(token.accessToken, token.refreshToken)
            }
        }
    }

    private fun checkRegistrationAction() {

        var vl = refreshErrorMessages()
        val email: String = viewState.value?.emailValue!!
        var isFieldError = false

        with(viewState) {
            if (value?.nameValue == "") {
                vl = vl.copy(nameTextErrorState = EditTextErrorState.IsEmpty)
                isFieldError = true
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                vl = vl.copy(emailTextErrorState = EditTextErrorState.IsNotRegex)
                isFieldError = true
            }

            if (value?.passwordValue == "") {
                vl = vl.copy(passTextErrorState = EditTextErrorState.IsEmpty)
                isFieldError = true
            }

            if (value?.passwordConfirmationValue != value?.passwordValue) {
                vl = vl.copy(passConfirmationTextErrorState = EditTextErrorState.IsNotValid)
                isFieldError = true
            }
        }
        _viewState.postValue(vl)

        if (!isFieldError) {
            signUp()
        }
    }
}
