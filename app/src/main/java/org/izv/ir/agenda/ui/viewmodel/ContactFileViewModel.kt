package org.izv.ir.agenda.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.izv.ir.agenda.model.Contact
import org.izv.ir.agenda.model.ContactFileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ContactFileViewModel (private val repository: ContactFileRepository) : ViewModel() {

    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    var contacts = _contacts.asStateFlow() //hacemos una copia PUBLICA para que la vea Compose

    fun readContacts () {
        viewModelScope.launch {
            _contacts.value = repository.readContacts()
            contacts = _contacts
        }
    }
}