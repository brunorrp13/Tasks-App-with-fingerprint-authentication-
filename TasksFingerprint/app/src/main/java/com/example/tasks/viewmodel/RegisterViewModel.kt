package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.listener.ValidationListener
import com.example.tasks.service.model.HeaderModel
import com.example.tasks.service.repository.PersonRepository
import com.example.tasks.service.repository.local.SecurityPreferences
import com.example.tasks.service.usecase.CreateUseCase
import com.example.tasks.service.usecase.LoginUseCase

class RegisterViewModel(
    application: Application,
    private val createUseCase: CreateUseCase
) : AndroidViewModel(application) {

    // Registry through API
    private val mCreate = MutableLiveData<ValidationListener>()
    val create: LiveData<ValidationListener> = mCreate

    // Data access
    private val mSecurityPreferences = SecurityPreferences(application)

    fun create(name: String, email: String, password: String) {
        createUseCase.execute(name, email, password, object : APIListener<HeaderModel>{
            override fun onSuccess(result: HeaderModel, statusCode: Int) {

                // Saving data to SharePreferences
                mSecurityPreferences.store(TaskConstants.SHARED.PERSON_KEY, result.personKey)
                mSecurityPreferences.store(TaskConstants.SHARED.TOKEN_KEY, result.token)
                mSecurityPreferences.store(TaskConstants.SHARED.PERSON_NAME, result.name)

                mCreate.value = ValidationListener()
            }

            override fun onFailure(message: String) {
            if (message.contains("connection", ignoreCase = true)) {
             mCreate.value = ValidationListener(message)
            } else {
                mCreate.value =
                    ValidationListener("All fields must be filled out and password must be at least 6 characters")
            }
            }

        })
    }

}