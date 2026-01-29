package com.edwindiaz.myapplication.features.login.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.edwindiaz.myapplication.features.login.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _message = MutableStateFlow("")
    val message=  _message.asStateFlow()


    fun onChangeUsername(it : String){
        _username.value = it
    }

    fun onChangePassword(it : String){
        _password.value = it
    }
}


