package com.example.tasks.service.usecase

import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.HeaderModel
import com.example.tasks.service.repository.PersonRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor (private val personRepository: PersonRepository) {

        fun execute(email: String, password: String, listener: APIListener<HeaderModel>) = personRepository.login(email, password, listener)

}