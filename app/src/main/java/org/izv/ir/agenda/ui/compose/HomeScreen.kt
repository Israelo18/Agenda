package org.izv.ir.agenda.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.izv.ir.agenda.ui.viewmodel.ContactFileViewModel

@Composable // FALTAN COSAS?
fun HomeScreen(
    navController: NavController,
    viewModel: ContactFileViewModel,
    innerPadding: PaddingValues) {
    val contactos = listOf("one", "two", "three", "four", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, bottom = 48.dp)
    ) {
        if (contactos.isEmpty()) {
            Text("No contacts found.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(items = contactos) { contacto ->
                    Item(navController)
                }
            }
        }
        Button(onClick = { navController.navigate("add-contact") }) {
            Text("Add Contact")
        }
    }
}