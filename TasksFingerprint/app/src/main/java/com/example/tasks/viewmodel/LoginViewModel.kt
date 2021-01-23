package com.example.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.listener.ValidationListener
import com.example.tasks.service.repository.local.SecurityPreferences
import com.example.tasks.service.constants.TaskConstants
import com.example.tasks.service.helper.FingerprintHelper
import com.example.tasks.service.model.HeaderModel
import com.example.tasks.service.repository.remote.RetrofitClient
import com.example.tasks.service.usecase.LoginUseCase
import javax.inject.Inject


class LoginViewModel @Inject constructor (
    application: Application,
    private val loginUseCase: LoginUseCase
): AndroidViewModel(application)  {

    // Data access
    private val mSecurityPreferences = SecurityPreferences(application)

    // Login through API
    private val mLogin = MutableLiveData<ValidationListener>()
    val login: LiveData<ValidationListener> = mLogin

    // Login through SharedPreferences
    private val mFingerprint = MutableLiveData<Boolean>()
    val fingerprint: LiveData<Boolean> = mFingerprint

    /**
     * Login through API
     */
    fun doLogin(email: String, password: String) {
        loginUseCase.execute(email, password, object : APIListener<HeaderModel> {
            override fun onSuccess(result: HeaderModel, statusCode: Int) {

                // Saving data to SharePreferences
                mSecurityPreferences.store(TaskConstants.SHARED.PERSON_KEY, result.personKey)
                mSecurityPreferences.store(TaskConstants.SHARED.TOKEN_KEY, result.token)
                mSecurityPreferences.store(TaskConstants.SHARED.PERSON_NAME, result.name)

                // Updates header's values for requisitions
                RetrofitClient.addHeaders(result.personKey, result.token)

                // Success alert
                mLogin.value = ValidationListener()
            }

            override fun onFailure(message: String) {
                if (message.contains("connection", ignoreCase = true)) {
                    mLogin.value = ValidationListener(message)
                } else {
                    mLogin.value = ValidationListener("E-mail and/or password invalid")
                }
                }
        })
    }

    fun isAuthenticationAvailable() {

        val personKey = mSecurityPreferences.get(TaskConstants.SHARED.PERSON_KEY)
        val tokenKey = mSecurityPreferences.get(TaskConstants.SHARED.TOKEN_KEY)

        // If token and person key are not empty, user is alredy logged
        val everLogged = (tokenKey != "" && personKey != "")

        // Updated requester's Hader values
        RetrofitClient.addHeaders(personKey, tokenKey)

            if (FingerprintHelper.isAuthenticationAvailable(getApplication())) {
            mFingerprint.value = everLogged
        }
    }

}