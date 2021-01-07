package business

import entity.ContactEntity
import repository.ContactRepository
import java.text.NumberFormat




class ContactBusiness {


    private fun validateContact (name: String, phone: String) {
        if (name == "") {
            throw Exception("Name is required!")
        }

        if (phone == "   -   -    ") {
            throw Exception("Phone is required!")
        }


    }

    fun save(name: String, phone: String) {
        validateContact(name, phone)
        val contact = ContactEntity(name, phone)
        ContactRepository.save(contact)
    }

    private fun validateDetele(name: String, phone: String) {
        if (name == "" || phone == "") {
            throw Exception("It's necessary to select a customer before removing it.")
        }
    }

    fun delete(name: String, phone: String) {
        validateDetele(name, phone)

        val contact = ContactEntity(name, phone)
        ContactRepository.delete(contact)

    }

    fun getList(): List<ContactEntity> {
        return ContactRepository.getList()
    }

    fun getContactCountDescription(): String {
        val list = getList()
        return when {
            list.isEmpty() -> "0 contatos"
            list.size == 1 -> "1 contato"
            else -> "${list.size} contatos"
        }
    }

}