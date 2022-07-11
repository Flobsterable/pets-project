package com.example.pets_project. ui.screens.login.model

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pets_project.repository.Repository
import com.example.pets_project.repository.RepositoryImpl
import com.example.pets_project.services.network.models.UserLoginData
import com.example.pets_project.ui.screens.login.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: Repository
)
    : ViewModel(), EventHandler<LoginEvent> {

    private val _viewState  = MutableLiveData(LoginViewState())
    val viewState : LiveData<LoginViewState> = _viewState


    override fun obtainEvent(event : LoginEvent) {
        when(event) {
            LoginEvent.SignInClicked -> signActionState(LoginSubState.Login)
            LoginEvent.SignUpClicked -> signActionState(LoginSubState.Registration)
            LoginEvent.ForgotButtonClicked -> TODO()
            LoginEvent.LoginButtonClicked -> checkServer()
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
    private fun nameChanged(value : String) {
        _viewState.postValue(_viewState.value?.copy(nameValue = value))
    }

    private fun refreshErrorMessages(): LoginViewState {

        return _viewState.value?.copy(
            nameTextErrorState = EditTextErrorState.None,
            emailTextErrorState = EditTextErrorState.None,
            passTextErrorState = EditTextErrorState.None,
            passConfirmationTextErrorState = EditTextErrorState.None)!!

    }
    private fun signActionState(loginSubState : LoginSubState) {

        var vl = refreshErrorMessages()

        vl= vl.copy(
            passwordValue = "",
            passwordConfirmationValue = "",
            loginSubState = loginSubState)

        _viewState.postValue(vl)
    }

    private fun checkServer(){
        viewModelScope.launch {
            repo.login(UserLoginData("email","pass"))
        }
    }

    private fun checkLoginAction() {

        var vl = refreshErrorMessages()
        val email : String = viewState.value?.emailValue!!

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            vl = vl.copy(emailTextErrorState = EditTextErrorState.IsNotRegex)

        if (viewState.value?.passwordValue == "")
           vl = vl.copy( passTextErrorState = EditTextErrorState.IsEmpty)

        vl = vl.copy(passwordValue = "")

        _viewState.postValue(vl)



    }

    private fun checkRegistrationAction() {

        var vl = refreshErrorMessages()
        val email: String = viewState.value?.emailValue!!

        with(viewState) {
        if (value?.nameValue == "")
            vl = vl.copy(nameTextErrorState = EditTextErrorState.IsEmpty)

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            vl = vl.copy(emailTextErrorState = EditTextErrorState.IsNotRegex)

        if (value?.passwordValue=="")
            vl = vl.copy(passTextErrorState = EditTextErrorState.IsEmpty)

        if(value?.passwordConfirmationValue!=value?.passwordValue)
            vl = vl.copy(passConfirmationTextErrorState = EditTextErrorState.IsNotValid)
        }
        _viewState.postValue(vl)
    }
}