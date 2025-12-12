package org.izv.ir.agenda.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.izv.ir.agenda.model.Contact
import org.izv.ir.agenda.model.ContactFileRepository
import org.izv.ir.agenda.ui.viewmodel.ContactFileViewModel

@Composable
fun EditContact(
    navController: NavController,
    viewModel: ContactFileViewModel,
    innerPadding: PaddingValues,
    id: Int
) {

    viewModel.readContacts()

    var name by remember { mutableStateOf(viewModel.contacts.value.get(id).name) }
    var phone by remember { mutableStateOf(viewModel.contacts.value.get(id).phone) }
    val context = LocalContext.current
    val repository = ContactFileRepository(context)
    val scope = rememberCoroutineScope() //para poder lanzar las corrutinas

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Text(
            text = "Edit Contact",
            style = MaterialTheme.typography.headlineMedium
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone) //teclado solo numerico
        )

        Button(onClick = {
            var modifyContact= Contact(id = viewModel.contacts.value.get(id).id, name = name, phone = phone)
            scope.launch {
                repository.editContact(modifyContact)
            }
            navController.popBackStack()
        }) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}