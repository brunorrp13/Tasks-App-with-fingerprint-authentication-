package com.example.tasks.view.di.login

import android.app.Application
import com.example.tasks.service.usecase.LoginUseCase
import com.example.tasks.view.login.LoginViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class LoginModule () {
   @Provides
   fun provideLoginViewModelFactory(
        application : Application,
        loginUseCase: LoginUseCase
    ): LoginViewModelFactory {
        return LoginViewModelFactory(
            application,
            loginUseCase
        )
    }


}