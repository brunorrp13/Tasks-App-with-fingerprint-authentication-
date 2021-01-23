package com.example.tasks.view.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.service.usecase.CreateUseCase
import com.example.tasks.service.usecase.LoginUseCase
import com.example.tasks.viewmodel.LoginViewModel
import com.example.tasks.viewmodel.RegisterViewModel
import javax.inject.Inject

class RegisterViewModelFactory

@Inject constructor (
    private val application: Application,
    private val createUseCase: CreateUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(application, createUseCase) as T
    }
}