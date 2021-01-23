package com.example.tasks.view.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.service.usecase.LoginUseCase
import com.example.tasks.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginViewModelFactory

@Inject constructor (
    private val application: Application,
    private val loginUseCase: LoginUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(application, loginUseCase) as T
    }
}