package com.example.tasks.service.usecase

import com.example.tasks.service.listener.APIListener
import com.example.tasks.service.model.HeaderModel
import com.example.tasks.service.repository.PersonRepository

class CreateUseCase(private val personRepository: PersonRepository) {

    fun execute(name: String, email: String, password: String, listener: APIListener<HeaderModel>) = personRepository.create(name, email, password, listener)

}