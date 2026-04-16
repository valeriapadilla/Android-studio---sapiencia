package com.upb.listadependientes.presentation.home

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.upb.listadependientes.viewModel.TareaViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ListaTareasScreen(
    viewModel: TareaViewModel,
    onNavigateToAdd: () -> Unit
) {

    val tareas by viewModel.tareas.collectAsState(initial = emptyList())
    val fechaActual = remember {
        val formato = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
        formato.format(Date())
    }
    val completas = tareas.count { it.estado }
    val incompletas = tareas.count { !it.estado }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAdd
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(8.dp)
        ) {

            item {
                InfoResumen(
                    fecha = fechaActual,
                    tareasCompletas = completas,
                    tareasPendientes  = incompletas
                )
            }

            items(tareas.size) { tarea ->
                ItemTarea(
                    task = tareas[tarea],
                    onClickItem = {
                    },
                    onDeleteItem = {
                        viewModel.eliminarTarea(it)
                    },
                    onToggleCompletion = {
                        viewModel.completarTarea(it)
                    }
                )
            }
        }
    }
}