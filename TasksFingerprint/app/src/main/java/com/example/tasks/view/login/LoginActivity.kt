package com.example.tasks.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.databinding.ActivityLoginBinding
import com.example.tasks.view.MainActivity
import com.example.tasks.view.register.RegisterActivity
import com.example.tasks.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.Executor
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var factory: LoginViewModelFactory
    private lateinit var mViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        mViewModel = ViewModelProvider(this,factory)
            .get(LoginViewModel::class.java)

        // Event initialization
        setListeners();
        observe()

        // Validates if the biometrical identification is available
        mViewModel.isAuthenticationAvailable()
    }

    override fun onClick(v: View) {
        if (v.id == binding.buttonLogin.id) {
            handleLogin()
        } else if (v.id == binding.textRegister.id) {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }


    private fun showAuthentication() {

        // Executor - Works as a thread for the authentication
        val executor: Executor = ContextCompat.getMainExecutor(this)

        // BiometricPrompt
        val biometricPrompt = BiometricPrompt(
            this@LoginActivity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }
            })

        // Information shown at the moment of the authentication
        val info: BiometricPrompt.PromptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.fp_titulo))
            .setSubtitle(getString(R.string.fp_subtitulo))
            .setDescription(getString(R.string.fp_descricao))
            .setNegativeButtonText(getString(R.string.fp_cancelar))
            .build()

        // Displays User
        biometricPrompt.authenticate(info)
    }

    /**
     * Click events initialization
     */
    private fun setListeners() {
        button_login.setOnClickListener(this)
        text_register.setOnClickListener(this)
    }

    /**
     * Observes ViewModel
     */
    private fun observe() {
        mViewModel.login.observe(this, Observer {
            if (it.success()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(applicationContext, it.failure(), Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.fingerprint.observe(this, Observer {
            if (it) {
                showAuthentication()
            }
        })
    }

    /**
     * User Authentication
     */
    private fun handleLogin() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        mViewModel.doLogin(email, password)
    }


}
