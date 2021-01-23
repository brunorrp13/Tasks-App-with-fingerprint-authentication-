package com.example.tasks.view.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tasks.R
import com.example.tasks.databinding.ActivityRegisterBinding
import com.example.tasks.view.MainActivity
import com.example.tasks.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var factory: RegisterViewModelFactory
    private lateinit var mViewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        mViewModel = ViewModelProvider(this,factory)
            .get(RegisterViewModel::class.java)

        // Event initialization
        listeners()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {

            val name = edit_name.text.toString()
            val email = edit_email.text.toString()
            val password = edit_password.text.toString()

            mViewModel.create(name, email, password)
        }
    }

    private fun observe() {
        mViewModel.create.observe(this, Observer {
            if (it.success()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(applicationContext, it.failure(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun listeners() {
        button_save.setOnClickListener(this)
    }
}
