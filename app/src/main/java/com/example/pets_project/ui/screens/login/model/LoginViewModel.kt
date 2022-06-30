package com.example.pets_project. ui.screens.login.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor()
    : ViewModel(), EventHandler<LoginEvent> {

    companion object {
        const val EMAIL_VALIDATION_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    }

    private val _viewState  = MutableLiveData(LoginViewState())
    val viewState : LiveData<LoginViewState> = _viewState

    override fun obtainEvent(event : LoginEvent){
        when(event){
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

    private fun refreshErrorMessages(){
        with(_viewState){
            postValue(value?.copy(nameTextErrorState = EditTextErrorState.None))
            postValue(value?.copy(emailTextErrorState = EditTextErrorState.None))
            postValue(value?.copy(passTextErrorState = EditTextErrorState.None))
            postValue(value?.copy(passConfirmationTextErrorState = EditTextErrorState.None))
        }
    }
    private fun signActionState(loginSubState : LoginSubState){
       _viewState.postValue(_viewState.value?.copy(loginSubState = loginSubState))
       // refreshErrorMessages()
     //   _viewState.postValue(_viewState.value?.copy(passwordValue = ""))
     //  _viewState.postValue(_viewState.value?.copy(passwordConfirmationValue = ""))
    }

    private fun emailChanged(value: String){
        _viewState.postValue(_viewState.value?.copy(emailValue = value))
    }
    private fun passChanged(value: String){
        _viewState.postValue(_viewState.value?.copy(passwordValue = value))
    }
    private fun passConfirmationChanged(value: String){
        _viewState.postValue(_viewState.value?.copy(passwordConfirmationValue = value))
    }
    private fun nameChanged(value : String){
        _viewState.postValue(_viewState.value?.copy(nameValue = value))
    }

    private fun checkLoginAction(){

        //пофиксить обработку
       _viewState.postValue(_viewState.value?.copy(emailTextErrorState = EditTextErrorState.None))
       _viewState.postValue(_viewState.value?.copy(passTextErrorState = EditTextErrorState.None))
///////////пофиксить
         if (!viewState.value?.emailValue!!.matches(Regex(EMAIL_VALIDATION_REGEX))){
            _viewState.postValue(_viewState.value?.copy(
                emailTextErrorState = EditTextErrorState.IsNotRegex))
            println(viewState.value!!.emailTextErrorState)}

        if (viewState.value?.passwordValue == "")
            _viewState.postValue(_viewState.value?.copy(
                passTextErrorState = EditTextErrorState.IsEmpty))
    }

    private fun checkRegistrationAction(){

        with(viewState){
        if (value?.nameValue == "")
            _viewState.postValue(_viewState.value?.copy(
                nameTextErrorState = EditTextErrorState.IsEmpty
            ))
        if (value?.emailValue!!.matches(Regex(EMAIL_VALIDATION_REGEX)))
            _viewState.postValue(_viewState.value?.copy(
                emailTextErrorState = EditTextErrorState.IsNotValid
            ))
        if (value?.passwordValue=="")
            _viewState.postValue(_viewState.value?.copy(
                passTextErrorState = EditTextErrorState.IsEmpty
            ))
        if(value?.passwordConfirmationValue!=value?.passwordValue)
            _viewState.postValue(_viewState.value?.copy(
                passConfirmationTextErrorState = EditTextErrorState.IsNotValid
            ))
        }
    }
}