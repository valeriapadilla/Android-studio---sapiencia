package com.upb.listadependientes.presentation.agregar

import android.R.attr.padding
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.upb.listadependientes.domain.Categoria
import com.upb.listadependientes.viewModel.TareaViewModel
import com.upb.listadependientes.domain.Tarea
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AgregarScreen(
    viewModel: TareaViewModel,
    onBack: () -> Unit
) {

    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var categoriaSeleccionada by remember { mutableStateOf(Categoria.PERSONAL) }
    var estaCompletada by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }

    val isValid = titulo.isNotBlank() && descripcion.isNotBlank()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva tarea") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->

        // 🔥 CONTENIDO (CON PADDING)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // 🔥 2. Row: Checkbox + Dropdown
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Is done")
                    Spacer(modifier = Modifier.width(8.dp))
                    Checkbox(
                        checked = estaCompletada,
                        onCheckedChange = { estaCompletada = it }
                    )
                }

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {

                    OutlinedTextField(
                        value = categoriaSeleccionada.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Category") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        Categoria.values().forEach { categoria ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        categoria.name.lowercase()
                                            .replaceFirstChar { it.uppercase() }
                                    )
                                },
                                onClick = {
                                    categoriaSeleccionada = categoria
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            // 📝 Título
            BasicTextField(
                value = titulo,
                onValueChange = { titulo = it },
                textStyle = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground
                ),
                decorationBox = { innerTextField ->
                    if (titulo.isEmpty()) {
                        Text(
                            "Task name",
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    innerTextField()
                },
                modifier = Modifier.fillMaxWidth()
            )

            // 📝 Descripción
            BasicTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                textStyle = MaterialTheme.typography.bodyLarge,
                decorationBox = { innerTextField ->
                    if (descripcion.isEmpty()) {
                        Text(
                            "Task description",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    innerTextField()
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            // 💾 Botón
            Button(
                onClick = {
                    viewModel.agregarTarea(
                        Tarea(
                            id = UUID.randomUUID().toString(),
                            titulo = titulo,
                            descripcion = descripcion,
                            estado = estaCompletada,
                            categoria = categoriaSeleccionada
                        )
                    )
                    onBack()
                },
                enabled = isValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Save")
            }
        }
    }
}